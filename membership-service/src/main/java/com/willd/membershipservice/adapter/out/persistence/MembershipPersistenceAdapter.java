package com.willd.membershipservice.adapter.out.persistence;

import com.willd.common.PersistenceAdapter;
import com.willd.membershipservice.application.port.out.FindMembershipPort;
import com.willd.membershipservice.application.port.out.ModifyMembershipPort;
import com.willd.membershipservice.application.port.out.RegisterMembershipPort;
import com.willd.membershipservice.domain.MembershipJpaEntity;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements FindMembershipPort, ModifyMembershipPort, RegisterMembershipPort {
    private final SpringDataMembershipRepository membershipRepository;

    @Override
    public MembershipJpaEntity findMembership(Long membershipId) {
        return membershipRepository.findById(membershipId).orElseThrow();
    }

    @Override
    public MembershipJpaEntity modifyMembership(
            Long membershipId,
            String membershipName,
            String membershipEmail,
            String membershipAddress,
            Boolean membershipIsValid,
            Boolean membershipIsCorp
    ) {
        MembershipJpaEntity membershipJpaEntity = membershipRepository.findById(membershipId).orElseThrow();
        membershipJpaEntity.modify(
                membershipName,
                membershipEmail,
                membershipAddress,
                membershipIsValid,
                membershipIsCorp
        );
        return membershipRepository.save(membershipJpaEntity);
    }

    @Override
    public MembershipJpaEntity registerMembership(
            String membershipName,
            String membershipEmail,
            String membershipAddress,
            Boolean membershipIsValid,
            Boolean membershipIsCorp
    ) {
        MembershipJpaEntity membershipJpaEntity = MembershipJpaEntity.register(
                membershipName,
                membershipEmail,
                membershipAddress,
                membershipIsValid,
                membershipIsCorp
        );
        return membershipRepository.save(membershipJpaEntity);
    }
}
