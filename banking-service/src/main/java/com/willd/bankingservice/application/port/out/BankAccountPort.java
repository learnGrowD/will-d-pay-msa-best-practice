package com.willd.bankingservice.application.port.out;

import com.willd.bankingservice.adapter.out.service.firmbanking.RequestFirmbanking;
import com.willd.bankingservice.adapter.out.service.firmbanking.Firmbanking;

public interface BankAccountPort {
    Firmbanking getBankAccount(String bankName, String bankAccountNumber);

    RequestFirmbanking requestFirmbanking(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber
    );
}
