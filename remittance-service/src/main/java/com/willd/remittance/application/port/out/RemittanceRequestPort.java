package com.willd.remittance.application.port.out;

import com.willd.domain.remittance.enums.RemittanceRequestStatus;
import com.willd.remittance.domain.RemittanceRequestR2dbcEntity;
import com.willd.domain.remittance.enums.RemittanceRequestType;
import reactor.core.publisher.Mono;

public interface RemittanceRequestPort {

    Mono<RemittanceRequestR2dbcEntity> create(
            Long remittanceFromMembershipId,
            Long toMembershipId,
            Integer amount,
            RemittanceRequestType type,
            RemittanceRequestStatus status,
            String uuid);

    Mono<RemittanceRequestR2dbcEntity> modify(
            RemittanceRequestR2dbcEntity entity
    );
}
