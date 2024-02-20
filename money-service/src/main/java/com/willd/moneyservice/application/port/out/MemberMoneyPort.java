package com.willd.moneyservice.application.port.out;

import com.willd.moneyservice.adapter.out.persistence.MemberMoneyJpaEntity;

public interface MemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(Long membershipId);
    MemberMoneyJpaEntity increaseMemberMoney(
            Long targetMembershipId,
            Integer increaseMoneyAmount
    );

    MemberMoneyJpaEntity decreaseMemberMoney(
            Long targetMembershipId,
            Integer increaseMoneyAmount
    );

    MemberMoneyJpaEntity createMemberMoney(
            Long membershipId
    );

    MemberMoneyJpaEntity modifyMemberMoney(MemberMoneyJpaEntity entity);
}
