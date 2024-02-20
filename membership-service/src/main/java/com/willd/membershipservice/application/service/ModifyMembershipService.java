package com.willd.membershipservice.application.service;

import com.willd.common.UserCase;
import com.willd.membershipservice.domain.MembershipJpaEntity;
import com.willd.membershipservice.adapter.out.persistence.MembershipMapper;
import com.willd.membershipservice.application.port.in.ModifyMembershipCommand;
import com.willd.membershipservice.application.port.in.ModifyMembershipUseCase;
import com.willd.membershipservice.application.port.out.ModifyMembershipPort;
import com.willd.domain.membership.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UserCase
@Transactional
@RequiredArgsConstructor
public class ModifyMembershipService implements ModifyMembershipUseCase {
    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity membershipJpaEntity = modifyMembershipPort.modifyMembership(
                command.getMembershipId(),
                command.getName(),
                command.getEmail(),
                command.getEmail(),
                command.getIsValid(),
                command.getIsCorp()
        );
        return membershipMapper.mapToDomainEntity(membershipJpaEntity);
    }
}
