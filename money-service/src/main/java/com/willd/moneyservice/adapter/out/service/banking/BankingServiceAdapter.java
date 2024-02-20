package com.willd.moneyservice.adapter.out.service.banking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willd.common.CommonHttpClient;
import com.willd.common.ServiceAdapter;
import com.willd.domain.bank.FirmbankingRequest;
import com.willd.domain.bank.RegisteredBankAccount;
import com.willd.moneyservice.application.port.out.BankingPort;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@ServiceAdapter
public class BankingServiceAdapter implements BankingPort {

    private final CommonHttpClient commonHttpClient;

    private final ObjectMapper objectMapper;
    private final String serviceBankingUrl;

    public BankingServiceAdapter(
            CommonHttpClient commonHttpClient,
            ObjectMapper objectMapper,
            @Value("${service.banking.url}") String serviceBankingUrl
    ) {
        this.commonHttpClient = commonHttpClient;
        this.objectMapper = objectMapper;
        this.serviceBankingUrl = serviceBankingUrl;

    }

    @Override
    public RegisteredBankAccount getBankingStatus(String bankAccountNumber) throws Exception {
        String url = serviceBankingUrl + "/banking/account/bank-account-number" + "/" + bankAccountNumber;
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            return objectMapper.readValue(jsonResponse, RegisteredBankAccount.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RegisteredBankAccount getBankingStatus(Long membershipId) throws Exception {
        String url = serviceBankingUrl + "/banking/account/membership-id" + "/" + membershipId;
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            return objectMapper.readValue(jsonResponse, RegisteredBankAccount.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FirmbankingRequest requestFirmbanking(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            Integer moneyAmount
    ) throws Exception {
        String url = serviceBankingUrl + "/banking/firmbanking/request";
        try {
            Map<String, Object> bodyMap = Map.of(
                    "fromBankName", fromBankName,
                    "fromBankAccountNumber", fromBankAccountNumber,
                    "toBankName", toBankName,
                    "toBankAccountNumber", toBankAccountNumber,
                    "moneyAmount", moneyAmount
            );
            String body = objectMapper.writeValueAsString(bodyMap);

            String jsonResponse = commonHttpClient.sendPostRequest(url, body).body();
            return objectMapper.readValue(jsonResponse, FirmbankingRequest.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
