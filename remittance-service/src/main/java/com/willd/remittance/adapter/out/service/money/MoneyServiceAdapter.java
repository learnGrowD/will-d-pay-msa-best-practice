package com.willd.remittance.adapter.out.service.money;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willd.common.CommonWebClient;
import com.willd.common.LoggingProducer;
import com.willd.common.ServiceAdapter;
import com.willd.domain.money.MemberMoney;
import com.willd.remittance.application.port.in.RequestRemittanceCommand;
import com.willd.remittance.application.port.out.MoneyPort;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import java.util.Map;

@ServiceAdapter
public class MoneyServiceAdapter implements MoneyPort {
    private final CommonWebClient commonWebClient;

    private final ObjectMapper objectMapper;

    private final String serviceMoneyUrl;

    public MoneyServiceAdapter(
            CommonWebClient commonWebClient,
            ObjectMapper objectMapper,
            @Value("${service.money.url}") String serviceMoneyUrl
    ) {
        this.commonWebClient = commonWebClient;
        this.objectMapper = objectMapper;
        this.serviceMoneyUrl = serviceMoneyUrl;
    }

    @Override
    public Mono<MemberMoney> getMoneyInfo(Long membershipId) {
        String url = serviceMoneyUrl + "/money/membership" + "/" + membershipId;
        return commonWebClient.sendGetRequestResultMono(url)
                .flatMap(jsonString -> {
                    try {
                        return Mono.just(objectMapper.readValue(jsonString, MemberMoney.class));
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                });
    }

    @Override
    public Mono<Boolean> requestMoneyIncrease(Long membershipId, Integer amount) {
        String url = serviceMoneyUrl + "/money/increase";
        Map<String, Object> body = Map.of(
                "targetMembershipId", membershipId,
                "increaseMoneyAmount", amount
        );
        return commonWebClient.sendPostRequestResultMono(url, body)
                .flatMap(jsonString -> {
                    try {
                        return Mono.just(objectMapper.readValue(jsonString, MemberMoney.class)).map(it -> true);
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                });
    }

    @Override
    public Mono<Boolean> requestMoneyDecrease(Long membershipId, Integer amount) {
        String url = serviceMoneyUrl + "/money/decrease";
        Map<String, Object> body = Map.of(
                "targetMembershipId", membershipId,
                "decreaseMoneyAmount", amount
        );
        return commonWebClient.sendPostRequestResultMono(url, body)
                .flatMap(jsonString -> {
                    try {
                        return Mono.just(objectMapper.readValue(jsonString, MemberMoney.class)).map(it -> true);
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                });
    }

    @Override
    public Mono<Boolean> checkBalanceAndRechargeIfInsufficientFunds(Long membershipId, Integer amount) {
        return getMoneyInfo(membershipId)
                .switchIfEmpty(Mono.error(new RuntimeException("Empty")))
                .flatMap(it -> {
                    if (it.getBalance() < amount) {
                        int rechargeAmount = (int) Math.ceil((amount - it.getBalance()) / 10000.0) * 10000;
                        return requestMoneyIncrease(membershipId, rechargeAmount);
                    } else {
                        return Mono.just(true);
                    }
                })
                .filter(it -> it);
    }

    @Override
    public Mono<?> remittanceToInternalCustomer(Long fromMembershipId, Long toMembershipId, Integer amount) {
        return Mono.zip(
                        requestMoneyDecrease(fromMembershipId, amount),
                        requestMoneyIncrease(toMembershipId, amount)
                )
                .filter(tuple -> tuple.getT1() && tuple.getT2())
                .switchIfEmpty(Mono.error(new RuntimeException("sefewfwe")));
    }
}
