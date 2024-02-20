package com.willd.bankingservice.application.port.in;

import com.willd.domain.bank.RegisteredBankAccount;

public interface RegisteredUseCase {

    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);

    RegisteredBankAccount getRegisterBankAccount(GetBankAccountByMembershipIdCommand command);

    RegisteredBankAccount getRegisteredBankAccount(GetBankAccountByBankAccountNumberCommand command);

}
