package com.willd.moneyservice.application.port.out;

import com.willd.domain.membership.Membership;

public interface MembershipPort {
    Membership getMembership(Long targetMembershipId) throws Exception;
}
