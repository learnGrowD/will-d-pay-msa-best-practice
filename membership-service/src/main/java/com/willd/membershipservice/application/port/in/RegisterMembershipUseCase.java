package com.willd.membershipservice.application.port.in;

import com.willd.domain.membership.Membership;

public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}
