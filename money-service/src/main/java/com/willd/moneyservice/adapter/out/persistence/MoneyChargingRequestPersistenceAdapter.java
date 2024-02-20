package com.willd.moneyservice.adapter.out.persistence;

import com.willd.common.PersistenceAdapter;
import com.willd.domain.money.enums.MoneyChargingRequestStatus;
import com.willd.moneyservice.application.port.out.MoneyChargingRequestPort;
import com.willd.domain.money.enums.ChangingType;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChargingRequestPersistenceAdapter implements MoneyChargingRequestPort {
    private final SpringDataMoneyChargingRequestRepository moneyChargingRequestRepository;
    @Override
    public MoneyChargingRequestJpaEntity createMoneyChargingRequest(
            Long targetMembershipId,
            ChangingType changingType,
            Integer chargingMoneyAmount,
            MoneyChargingRequestStatus changingMoneyStatus
    ) {
        MoneyChargingRequestJpaEntity entity = MoneyChargingRequestJpaEntity.register(
                targetMembershipId,
                changingType,
                chargingMoneyAmount,
                changingMoneyStatus
        );
        return moneyChargingRequestRepository.save(entity);
    }

    @Override
    public MoneyChargingRequestJpaEntity modifyMoneyChargingRequest(MoneyChargingRequestJpaEntity entity) {
        return moneyChargingRequestRepository.save(entity);
    }
}
