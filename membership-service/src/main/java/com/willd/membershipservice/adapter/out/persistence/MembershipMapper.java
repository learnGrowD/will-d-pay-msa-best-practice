package com.willd.membershipservice.adapter.out.persistence;

import com.willd.domain.membership.Membership;
import com.willd.membershipservice.domain.MembershipJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {
    public Membership mapToDomainEntity(MembershipJpaEntity entity) {
        return Membership.generate(
                entity.getMembershipId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getIsValid(),
                entity.getIsCorp()
        );
    }
}
