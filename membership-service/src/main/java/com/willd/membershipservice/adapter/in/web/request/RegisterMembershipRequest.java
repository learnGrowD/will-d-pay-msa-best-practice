package com.willd.membershipservice.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class RegisterMembershipRequest {
    private String name;

    private String email;

    private String address;

    private Boolean isCorp;
}
