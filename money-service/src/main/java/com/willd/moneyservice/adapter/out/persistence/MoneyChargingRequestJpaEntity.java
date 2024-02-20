package com.willd.moneyservice.adapter.out.persistence;

import com.willd.domain.money.enums.ChangingType;
import com.willd.domain.money.enums.MoneyChargingRequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Table(name = "money_charging_request")
@Entity
@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChargingRequestJpaEntity {
    @Id
    @GeneratedValue
    private Long moneyChargingRequestId;

    private Long targetMembershipId;

    @Enumerated(EnumType.STRING)
    private ChangingType changingType;

    private Integer chargingMoneyAmount;

    @Enumerated(EnumType.STRING)
    private MoneyChargingRequestStatus changingMoneyStatus;

    private String uuid;

    private Date createdAt;

    public static MoneyChargingRequestJpaEntity register(
            Long targetMembershipId,
            ChangingType changingType,
            Integer chargingMoneyAmount,
            MoneyChargingRequestStatus changingMoneyStatus
    ) {
        return MoneyChargingRequestJpaEntity.builder()
                .targetMembershipId(targetMembershipId)
                .changingType(changingType)
                .chargingMoneyAmount(chargingMoneyAmount)
                .changingMoneyStatus(changingMoneyStatus)
                .uuid(UUID.randomUUID().toString())
                .createdAt(new Date())
                .build();
    }

    public void modify(
            Long targetMembershipId,
            ChangingType changingType,
            Integer chargingMoneyAmount,
            MoneyChargingRequestStatus changingMoneyStatus
    ) {
        if (targetMembershipId != null) setTargetMembershipId(targetMembershipId);
        if (changingType != null) setChangingType(changingType);
        if (chargingMoneyAmount != null) setChargingMoneyAmount(chargingMoneyAmount);
        if (changingMoneyStatus != null) setChangingMoneyStatus(changingMoneyStatus);
    }
}
