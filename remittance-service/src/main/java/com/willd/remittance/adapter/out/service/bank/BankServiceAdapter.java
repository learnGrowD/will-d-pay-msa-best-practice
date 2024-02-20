package com.willd.remittance.adapter.out.service.bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.willd.common.CommonWebClient;
import com.willd.common.ServiceAdapter;
import com.willd.domain.bank.FirmbankingRequest;
import com.willd.domain.bank.RegisteredBankAccount;
import com.willd.domain.bank.enums.FirmbankingRequestStatus;
import com.willd.remittance.application.port.in.RequestRemittanceCommand;
import com.willd.remittance.application.port.out.BankPort;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@ServiceAdapter
public class BankServiceAdapter implements BankPort {
    private final CommonWebClient commonWebClient;
    private final ObjectMapper objectMapper;

    private final String serviceBankUrl;

    public BankServiceAdapter(
            CommonWebClient commonWebClient,
            ObjectMapper objectMapper,
            @Value("${service.banking.url}") String serviceBankUrl
    ) {
        this.commonWebClient = commonWebClient;
        this.objectMapper = objectMapper;
        this.serviceBankUrl = serviceBankUrl;
    }

    @Override
    public Mono<RegisteredBankAccount> getBankAccount(Long membershipId) {
        String url = serviceBankUrl + "/banking/account/membership-id" + "/" + membershipId;
        return commonWebClient.sendGetRequestResultMono(url)
                .flatMap(jsonString -> {
                    try {
                        return Mono.just(objectMapper.readValue(jsonString, RegisteredBankAccount.class));
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                });
    }

    @Override
    public Mono<Boolean> requestFirmbanking(
            String fromBankName,
            String fromBankAccountName,
            String toBankName,
            String toBankAccountName,
            Integer moneyAmount
    ) {
        String url = serviceBankUrl + "/banking/firmbanking/request";
        Map<String, Object> body = Map.of(
                "fromBankName", fromBankName,
                "fromBankAccountNumber", fromBankAccountName,
                "toBankName", toBankName,
                "toBankAccountNumber", toBankAccountName,
                "moneyAmount", moneyAmount
        );
        return commonWebClient.sendPostRequestResultMono(url, body)
                .flatMap(jsonString -> {
                    try {
                        return Mono.just(objectMapper.readValue(jsonString, FirmbankingRequest.class)).map(it -> it.getFirmbankingStatus().equals(FirmbankingRequestStatus.SUCCESS));
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                });
    }

    @Override
    public Mono<?> remittanceToExternalCustomer(Long fromMembershipId, Long toMembershipId, Integer amount) {
        return Mono.zip(
                        getBankAccount(fromMembershipId),
                        getBankAccount(toMembershipId)
                )
                .switchIfEmpty(Mono.error(new RuntimeException("")))
                .filter(tuple -> tuple.getT1().getLinkedStatusIsValid() && tuple.getT2().getLinkedStatusIsValid())
                .flatMap(tuple -> {
                    RegisteredBankAccount fromBankAccount = tuple.getT1();
                    RegisteredBankAccount toBankAccount = tuple.getT2();
                    return requestFirmbanking(
                            fromBankAccount.getBankName(),
                            fromBankAccount.getBankAccountNumber(),
                            toBankAccount.getBankName(),
                            toBankAccount.getBankAccountNumber(),
                            amount
                    );
                })
                .filter(it -> it)
                .switchIfEmpty(Mono.error(new RuntimeException("")));
    }
}
