package com.willd.tastconsumer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTaskHistoryRepository extends JpaRepository<RequestTaskHistoryJpaEntity, Long> {

}
