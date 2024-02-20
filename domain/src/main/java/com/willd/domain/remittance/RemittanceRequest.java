package com.willd.domain.remittance;

import com.willd.domain.remittance.enums.RemittanceRequestStatus;
import com.willd.domain.remittance.enums.RemittanceRequestType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RemittanceRequest {
    private Long remittanceRequestId;

    private Long remittanceFromMembershipId;

    private Long toMembershipId;

    private Integer amount;

    private RemittanceRequestType type;

    private RemittanceRequestStatus remittanceStatus;

    private LocalDateTime createdAt;

    private String uuid;

    public static RemittanceRequest generate(
            Long remittanceRequestId,
            Long remittanceFromMembershipId,
            Long toMembershipId,
            Integer remittanceAmount,
            RemittanceRequestType remittanceRequestType,
            RemittanceRequestStatus remittanceStatus,
            LocalDateTime createdAt,
            String uuid
    ) {
        return new RemittanceRequest(
                remittanceRequestId,
                remittanceFromMembershipId,
                toMembershipId,
                remittanceAmount,
                remittanceRequestType,
                remittanceStatus,
                createdAt,
                uuid
        );
    }
}
