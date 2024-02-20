package com.willd.moneyservice.adapter.out.persistence;

import com.willd.moneyservice.domain.MemberMoneyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {
    //select * from member_money where membership_id = ? order by id desc limit 1;
    Optional<MemberMoneyJpaEntity> findFirstByMembershipIdOrderByMemberMoneyIdDesc(Long membershipId);
}
