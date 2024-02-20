package com.willd.remittance.application.port.out;

import com.willd.domain.money.MemberMoney;
import reactor.core.publisher.Mono;

public interface MoneyPort {
    Mono<MemberMoney> getMoneyInfo(Long membershipId);

    Mono<Boolean> requestMoneyIncrease(Long membershipId, Integer amount);

    Mono<Boolean> requestMoneyDecrease(Long membershipIdm, Integer amount);

    Mono<Boolean> checkBalanceAndRechargeIfInsufficientFunds(Long membershipId, Integer amount);

    Mono<?> remittanceToInternalCustomer(Long fromMembershipId, Long toMembershipId, Integer amount);
}
