package com.willd.membershipservice.application.port.in;

import com.willd.common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {
    @NotBlank
    private final String name;

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String address;

    @AssertTrue
    private final Boolean isValid;

    private final Boolean isCorp;

    public RegisterMembershipCommand(
            String name,
            String email,
            String address,
            Boolean isValid,
            Boolean isCorp
    ) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorp = isCorp;

        validateSelf();
    }
}
