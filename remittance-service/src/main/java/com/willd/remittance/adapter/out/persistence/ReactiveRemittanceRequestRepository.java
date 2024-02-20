package com.willd.remittance.adapter.out.persistence;

import com.willd.remittance.domain.RemittanceRequestR2dbcEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReactiveRemittanceRequestRepository extends ReactiveCrudRepository<RemittanceRequestR2dbcEntity, Long> {

}
