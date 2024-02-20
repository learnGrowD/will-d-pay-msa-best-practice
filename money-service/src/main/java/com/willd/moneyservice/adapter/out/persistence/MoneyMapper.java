package com.willd.moneyservice.adapter.out.persistence;

import com.willd.domain.money.MemberMoney;
import com.willd.domain.money.MoneyChargingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyMapper {

    public MemberMoney mapToDomainEntity(MemberMoneyJpaEntity entity) {
        return MemberMoney.generate(
                entity.getMemberMoneyId(),
                entity.getMembershipId(),
                entity.getBalance()
        );
    }

    public MoneyChargingRequest mapToDomainEntity(MoneyChargingRequestJpaEntity entity) {
        return MoneyChargingRequest.generate(
                entity.getMoneyChargingRequestId(),
                entity.getTargetMembershipId(),
                entity.getChangingType(),
                entity.getChargingMoneyAmount(),
                entity.getChangingMoneyStatus(),
                entity.getUuid(),
                entity.getCreatedAt()
        );
    }
}
