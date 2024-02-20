package com.willd.membershipservice.adapter.out.persistence;

import com.willd.membershipservice.domain.MembershipJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMembershipRepository extends JpaRepository<MembershipJpaEntity, Long> {
}
