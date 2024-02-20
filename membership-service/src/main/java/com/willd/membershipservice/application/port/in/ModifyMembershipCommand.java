package com.willd.membershipservice.application.port.in;

import com.willd.common.SelfValidating;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipCommand> {
    @NotNull
    private final Long membershipId;

    @NotBlank
    private final String name;

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String address;

    @AssertTrue
    private final Boolean isValid;

    private final Boolean isCorp;

    public ModifyMembershipCommand(
            Long membershipId,
            String name,
            String email,
            String address,
            Boolean isValid,
            Boolean isCorp
    ) {
        this.membershipId = membershipId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorp = isCorp;

        validateSelf();
    }
}
