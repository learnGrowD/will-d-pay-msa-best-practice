package com.willd.bankingservice.adapter.out.service.firmbanking;

import com.willd.bankingservice.application.port.out.BankAccountPort;
import com.willd.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountExternalServiceAdapter implements BankAccountPort {

    @Override
    public Firmbanking getBankAccount(String bankName, String bankAccountNumber) {
        return Firmbanking.builder()
                .bankName(bankName)
                .bankAccountNumber(bankAccountNumber)
                .isValid(true)
                .build();
    }

    @Override
    public RequestFirmbanking requestFirmbanking(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber
    ) {
        return RequestFirmbanking.builder()
                .resultCode(RequestFirmbankingStatus.SUCCESS)
                .build();
    }
}
