package com.willd.remittance.adapter.out.persistence;

import com.willd.domain.remittance.RemittanceRequest;
import com.willd.domain.remittance.enums.RemittanceRequestStatus;
import com.willd.domain.remittance.enums.RemittanceRequestType;
import org.springframework.stereotype.Component;

@Component
public class RemittanceRequestMapper {

    public RemittanceRequest mapToDomainEntity(RemittanceRequestR2dbcEntity entity) {
        return RemittanceRequest.generate(
                entity.getId(),
                entity.getRemittanceFromMembershipId(),
                entity.getToMembershipId(),
                entity.getAmount(),
                RemittanceRequestType.valueOf(entity.getRemittanceRequestType()),
                RemittanceRequestStatus.valueOf(entity.getRemittanceRequestStatus()),
                entity.getCreatedAt(),
                entity.getUuid()
        );
    }
}
