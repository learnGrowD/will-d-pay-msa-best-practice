package com.willd.membershipservice.application.port.in;

import com.willd.domain.membership.Membership;

public interface ModifyMembershipUseCase {
    Membership modifyMembership(ModifyMembershipCommand command);
}
