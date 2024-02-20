package com.willd.membershipservice.application.port.out;

import com.willd.membershipservice.domain.MembershipJpaEntity;

public interface ModifyMembershipPort {

    MembershipJpaEntity modifyMembership(
            Long membershipId,
            String membershipName,
            String membershipEmail,
            String membershipAddress,
            Boolean membershipIsValid,
            Boolean membershipIsCorp
    );
}
