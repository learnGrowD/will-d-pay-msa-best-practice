package com.willd.membershipservice.application.port.in;

import com.willd.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {

    @NotNull
    private final Long membershipId;

    public FindMembershipCommand(Long membershipId) {
        this.membershipId = membershipId;

        validateSelf();
    }

}
