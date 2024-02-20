package com.willd.remittance.application.port.in;

import com.willd.common.SelfValidating;
import com.willd.domain.remittance.enums.RemittanceRequestType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RequestRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {
    @NotNull
    private Long fromMembershipId;

    @NotNull
    private Long toMembershipId;

    @NotNull
    private Integer amount;

    private RemittanceRequestType type;

    public RequestRemittanceCommand(
            Long fromMembershipId,
            Long toMembershipId,
            Integer amount,
            RemittanceRequestType type
    ) {
        this.fromMembershipId = fromMembershipId;
        this.toMembershipId = toMembershipId;
        this.amount = amount;
        this.type = type;

        this.validateSelf();
    }
}
