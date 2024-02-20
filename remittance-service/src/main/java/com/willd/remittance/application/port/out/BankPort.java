package com.willd.remittance.application.port.out;

import com.willd.domain.bank.RegisteredBankAccount;
import reactor.core.publisher.Mono;

public interface BankPort {
    Mono<RegisteredBankAccount> getBankAccount(Long membershipId);
    Mono<Boolean> requestFirmbanking(
            String fromBankName,
            String fromBankAccountName,
            String toBankName,
            String toBankAccountName,
            Integer moneyAmount
    );

    Mono<?> remittanceToExternalCustomer(Long fromMembershipId, Long toMembershipId, Integer amount);
}
