package com.willd.bankingservice.adapter.out.persistence;

import com.willd.bankingservice.application.port.out.RegisteredBankAccountPort;
import com.willd.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisteredBankAccountPort {
    private final SpringDataRegisteredBankAccountRepository registeredBankAccountRepository;

    @Override
    public RegisteredBankAccountJpaEntity createBankAccount(
            Long membershipId,
            String bankName,
            String bankAccountNumber,
            Boolean linkedStatusIsValid
    ) {
        return registeredBankAccountRepository.save(
                RegisteredBankAccountJpaEntity.register(
                        membershipId,
                        bankName,
                        bankAccountNumber,
                        linkedStatusIsValid
                )
        );
    }

    @Override
    public RegisteredBankAccountJpaEntity getRegisteredBankAccount(String bankAccountNumber) {
        return registeredBankAccountRepository.findFirstByBankAccountNumberOrderByRegisteredBankAccountIdDesc(bankAccountNumber).orElseThrow(() -> new RuntimeException("RegisteredBankAccountJpaEntity is Null bankAccountNumber is " + bankAccountNumber));
    }

    @Override
    public RegisteredBankAccountJpaEntity getRegisteredBankAccount(Long membershipId) {
        return registeredBankAccountRepository.findFirstByMembershipIdOrderByRegisteredBankAccountIdDesc(membershipId).orElseThrow(() -> new RuntimeException("RegisteredBankAccountJpaEntity is Null membershipId is " + membershipId));
    }
}
