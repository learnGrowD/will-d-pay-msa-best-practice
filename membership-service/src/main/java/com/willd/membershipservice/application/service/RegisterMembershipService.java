package com.willd.membershipservice.application.service;

import com.willd.common.UserCase;
import com.willd.membershipservice.domain.MembershipJpaEntity;
import com.willd.membershipservice.adapter.out.persistence.MembershipMapper;
import com.willd.membershipservice.application.port.in.RegisterMembershipCommand;
import com.willd.membershipservice.application.port.in.RegisterMembershipUseCase;
import com.willd.membershipservice.application.port.out.RegisterMembershipPort;
import com.willd.domain.membership.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UserCase
@Transactional
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUseCase {
    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        MembershipJpaEntity membershipJpaEntity = registerMembershipPort.registerMembership(
                command.getName(),
                command.getEmail(),
                command.getAddress(),
                command.getIsValid(),
                command.getIsCorp()
        );
        return membershipMapper.mapToDomainEntity(membershipJpaEntity);
    }
}
