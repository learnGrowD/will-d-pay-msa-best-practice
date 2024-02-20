package com.willd.membershipservice.application.service;

import com.willd.common.UserCase;
import com.willd.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.willd.membershipservice.adapter.out.persistence.MembershipMapper;
import com.willd.membershipservice.application.port.in.FindMembershipCommand;
import com.willd.membershipservice.application.port.in.FindMembershipUseCase;
import com.willd.membershipservice.application.port.out.FindMembershipPort;
import com.willd.domain.membership.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UserCase
@Transactional
@RequiredArgsConstructor
public class FindMembershipService implements FindMembershipUseCase {
    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity membershipJpaEntity = findMembershipPort.findMembership(command.getMembershipId());
        return membershipMapper.mapToDomainEntity(membershipJpaEntity);
    }
}
