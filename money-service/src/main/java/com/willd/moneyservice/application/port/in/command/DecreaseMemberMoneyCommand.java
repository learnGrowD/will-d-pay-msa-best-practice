package com.willd.moneyservice.application.port.in.command;

import com.willd.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class DecreaseMemberMoneyCommand extends SelfValidating<DecreaseMemberMoneyCommand> {
    @NotNull
    private Long targetMembershipId;

    @NotNull
    private Integer decreaseMoneyAmount;

    public DecreaseMemberMoneyCommand(Long targetMembershipId, Integer decreaseMoneyAmount) {
        this.targetMembershipId = targetMembershipId;
        this.decreaseMoneyAmount = decreaseMoneyAmount;

        this.validateSelf();
    }
}
