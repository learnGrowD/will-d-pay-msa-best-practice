package com.willd.bankingservice.application.port.out;

import com.willd.domain.membership.Membership;

public interface MembershipPort {
    Membership getMemberStatus(Long membershipId);
}
