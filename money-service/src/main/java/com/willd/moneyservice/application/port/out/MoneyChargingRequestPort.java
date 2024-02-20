package com.willd.moneyservice.application.port.out;

import com.willd.domain.money.enums.MoneyChargingRequestStatus;
import com.willd.moneyservice.adapter.out.persistence.MoneyChargingRequestJpaEntity;
import com.willd.domain.money.enums.ChangingType;

public interface MoneyChargingRequestPort {
    MoneyChargingRequestJpaEntity createMoneyChargingRequest(
            Long targetMembershipId,
            ChangingType changingType,
            Integer chargingMoneyAmount,
            MoneyChargingRequestStatus changingMoneyStatus
    );

    MoneyChargingRequestJpaEntity modifyMoneyChargingRequest(MoneyChargingRequestJpaEntity entity);

}
