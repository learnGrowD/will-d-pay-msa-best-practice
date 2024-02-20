package com.willd.remittance.application.port.out;

import com.willd.domain.membership.Membership;
import reactor.core.publisher.Mono;

public interface MembershipPort {
    Mono<Membership> getMembershipStatus(Long membershipId);

    Mono<Membership> checkMembershipValidity(Long membershipId);

}
