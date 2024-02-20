package com.willd.membershipservice.adapter.in.web;

import com.willd.common.WebAdapter;
import com.willd.membershipservice.adapter.in.web.request.RegisterMembershipRequest;
import com.willd.membershipservice.application.port.in.RegisterMembershipCommand;
import com.willd.membershipservice.application.port.in.RegisterMembershipUseCase;
import com.willd.domain.membership.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {
    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping(path = "membership/register")
    public Membership registerMembership(@RequestBody RegisterMembershipRequest request) {
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .isValid(true)
                .isCorp(request.getIsCorp())
                .build();

        return registerMembershipUseCase.registerMembership(command);
    }
}
