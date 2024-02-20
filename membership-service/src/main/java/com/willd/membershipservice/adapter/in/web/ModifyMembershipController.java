package com.willd.membershipservice.adapter.in.web;

import com.willd.common.WebAdapter;
import com.willd.membershipservice.adapter.in.web.request.ModifyMembershipRequest;
import com.willd.membershipservice.application.port.in.ModifyMembershipCommand;
import com.willd.membershipservice.application.port.in.ModifyMembershipUseCase;
import com.willd.domain.membership.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {
    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PostMapping(path = "membership/modify")
    public Membership modifyMembership(@RequestBody ModifyMembershipRequest request) {
        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
                .membershipId(request.getMembershipId())
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .isValid(true)
                .isCorp(request.getIsCorp())
                .build();
        return modifyMembershipUseCase.modifyMembership(command);
    }
}
