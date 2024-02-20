package com.willd.domain.money;

import com.willd.domain.money.enums.ChangingType;
import com.willd.domain.money.enums.MoneyChargingRequestStatus;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChargingRequest {
    private Long moneyChargingRequestId;

    private Long targetMembershipId;

    private ChangingType changingType;

    private Integer chargingMoneyAmount;

    private MoneyChargingRequestStatus changingMoneyStatus;

    private String uuid;

    private Date createdAt;

    public static MoneyChargingRequest generate(
            Long moneyChargingRequestId,
            Long targetMembershipId,
            ChangingType changingType,
            Integer chargingMoneyAmount,
            MoneyChargingRequestStatus changingMoneyStatus,
            String uuid,
            Date createdAt
    ) {
        return new MoneyChargingRequest(
                moneyChargingRequestId,
                targetMembershipId,
                changingType,
                chargingMoneyAmount,
                changingMoneyStatus,
                uuid,
                createdAt
        );
    }
}
