package com.willd.membershipservice.application.port.out;

import com.willd.membershipservice.domain.MembershipJpaEntity;

public interface FindMembershipPort {
    MembershipJpaEntity findMembership(Long membershipId);
}
