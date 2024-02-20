package com.willd.bankingservice.adapter.out.persistence;

import com.willd.bankingservice.domain.FirmbankingRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataFirmbankingRequestRepository extends JpaRepository<FirmbankingRequestJpaEntity, Long> {
}
