package com.willd.moneyservice.adapter.out.persistence;

import com.willd.common.PersistenceAdapter;
import com.willd.moneyservice.application.port.out.MemberMoneyPort;
import com.willd.moneyservice.domain.MemberMoneyJpaEntity;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberMoneyPersistenceAdapter implements MemberMoneyPort {
    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MemberMoneyJpaEntity getMemberMoney(Long membershipId) {
        return memberMoneyRepository.findFirstByMembershipIdOrderByMemberMoneyIdDesc(membershipId).orElseThrow();
    }

    @Override
    public MemberMoneyJpaEntity increaseMemberMoney(Long targetMembershipId, Integer increaseMoneyAmount) {
        MemberMoneyJpaEntity entity = memberMoneyRepository.findFirstByMembershipIdOrderByMemberMoneyIdDesc(targetMembershipId).orElseGet(() -> createMemberMoney(targetMembershipId));
        Integer resultMoney = entity.getBalance() + increaseMoneyAmount;
        entity.setBalance(resultMoney);
        return modifyMemberMoney(entity);
    }

    @Override
    public MemberMoneyJpaEntity decreaseMemberMoney(Long targetMembershipId, Integer decreaseMoneyAmount) {
        MemberMoneyJpaEntity entity = memberMoneyRepository.findFirstByMembershipIdOrderByMemberMoneyIdDesc(targetMembershipId).orElseThrow();
        Integer resultMoney = entity.getBalance() - decreaseMoneyAmount;
        if (resultMoney < 0) throw new RuntimeException("");
        entity.setBalance(resultMoney);
        return modifyMemberMoney(entity);
    }

    @Override
    public MemberMoneyJpaEntity createMemberMoney(
            Long membershipId
    ) {
        MemberMoneyJpaEntity entity = MemberMoneyJpaEntity.register(
                membershipId
        );
        return memberMoneyRepository.save(entity);
    }

    @Override
    public MemberMoneyJpaEntity modifyMemberMoney(MemberMoneyJpaEntity entity) {
        return memberMoneyRepository.save(entity);
    }
}
