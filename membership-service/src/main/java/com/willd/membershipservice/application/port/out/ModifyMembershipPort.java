package com.willd.membershipservice.application.port.out;

import com.willd.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.willd.domain.membership.Membership;

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
