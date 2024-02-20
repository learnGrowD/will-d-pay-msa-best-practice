package com.willd.moneyservice.application.port.out;

import com.willd.domain.bank.FirmbankingRequest;
import com.willd.domain.bank.RegisteredBankAccount;

public interface BankingPort {

    RegisteredBankAccount getBankingStatus(String bankAccountNumber) throws Exception;
    RegisteredBankAccount getBankingStatus(Long membershipId) throws Exception;

    FirmbankingRequest requestFirmbanking(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            Integer moneyAmount
    ) throws Exception;
}
