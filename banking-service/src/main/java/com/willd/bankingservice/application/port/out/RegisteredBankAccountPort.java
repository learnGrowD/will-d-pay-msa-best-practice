package com.willd.bankingservice.application.port.out;

import com.willd.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;

public interface RegisteredBankAccountPort {
    RegisteredBankAccountJpaEntity createBankAccount(
            Long membershipId,
            String bankName,
            String bankAccountNumber,
            Boolean linkedStatusIsValid
    );

    RegisteredBankAccountJpaEntity getRegisteredBankAccount(String bankAccountNumber);

    RegisteredBankAccountJpaEntity getRegisteredBankAccount(Long membershipId);

}
