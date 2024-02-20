package com.willd.bankingservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface SpringDataRegisteredBankAccountRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {

    //select * from registered_bank_account where membershipId = ? order by id desc limit 1;
    Optional<RegisteredBankAccountJpaEntity> findFirstByMembershipIdOrderByRegisteredBankAccountIdDesc(Long membershipId);

    //select * from registered_bank_account where bank_account_number = ? order by id desc limit 1;
    Optional<RegisteredBankAccountJpaEntity> findFirstByBankAccountNumberOrderByRegisteredBankAccountIdDesc(String bankAccountNumber);
}
