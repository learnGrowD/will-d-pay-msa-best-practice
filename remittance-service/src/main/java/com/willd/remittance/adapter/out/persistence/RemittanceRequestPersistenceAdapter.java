package com.willd.remittance.adapter.out.persistence;

import com.willd.common.PersistenceAdapter;
import com.willd.domain.remittance.enums.RemittanceRequestStatus;
import com.willd.remittance.application.port.out.RemittanceRequestPort;
import com.willd.domain.remittance.enums.RemittanceRequestType;
import com.willd.remittance.domain.RemittanceRequestR2dbcEntity;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class RemittanceRequestPersistenceAdapter implements RemittanceRequestPort {
    private final ReactiveRemittanceRequestRepository remittanceRepository;

    @Override
    public Mono<RemittanceRequestR2dbcEntity> create(
            Long remittanceFromMembershipId,
            Long toMembershipId,
            Integer amount,
            RemittanceRequestType type,
            RemittanceRequestStatus status,
            String uuid
    ) {
        return remittanceRepository.save(
                RemittanceRequestR2dbcEntity.generate(
                        remittanceFromMembershipId,
                        toMembershipId,
                        amount,
                        type,
                        status,
                        uuid
                )
        );
    }

    @Override
    public Mono<RemittanceRequestR2dbcEntity> modify(RemittanceRequestR2dbcEntity entity) {
        return remittanceRepository.save(entity);
    }
}
