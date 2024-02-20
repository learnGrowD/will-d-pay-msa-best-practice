package com.willd.moneyservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMoneyChargingRequestRepository extends JpaRepository<MoneyChargingRequestJpaEntity, Long> {
}
