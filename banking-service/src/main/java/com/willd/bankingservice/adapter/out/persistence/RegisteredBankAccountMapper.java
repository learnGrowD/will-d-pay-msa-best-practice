package com.willd.bankingservice.adapter.out.persistence;

import com.willd.bankingservice.domain.RegisteredBankAccountJpaEntity;
import com.willd.domain.bank.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {

    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity entity) {
        return RegisteredBankAccount.generate(
                entity.getRegisteredBankAccountId(),
                entity.getMembershipId(),
                entity.getBankName(),
                entity.getBankAccountNumber(),
                entity.getLinkedStatusIsValid()
        );
    }
}
