package com.willd.remittance.domain;

import com.willd.domain.remittance.enums.RemittanceRequestStatus;
import com.willd.domain.remittance.enums.RemittanceRequestType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "remittance_request")
@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RemittanceRequestR2dbcEntity {
    @Id
    private Long id;

    private Long remittanceFromMembershipId;

    private Long toMembershipId;

    private Integer amount;

    private String remittanceRequestType;

    private String remittanceRequestStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    private String uuid;

    public static RemittanceRequestR2dbcEntity generate(
            Long remittanceFromMembershipId,
            Long toMembershipId,
            Integer amount,
            RemittanceRequestType type,
            RemittanceRequestStatus status,
            String uuid
    ) {
        return RemittanceRequestR2dbcEntity.builder()
                .remittanceFromMembershipId(remittanceFromMembershipId)
                .toMembershipId(toMembershipId)
                .amount(amount)
                .remittanceRequestType(type.name())
                .remittanceRequestStatus(status.name())
                .uuid(uuid)
                .build();
    }
}
