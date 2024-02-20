package com.willd.moneyservice.application.port.in.command;

import com.willd.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class IncreaseMemberMoneyCommand extends SelfValidating<IncreaseMemberMoneyCommand> {
    @NotNull
    private Long targetMembershipId;

    @NotNull
    private Integer increaseMoneyAmount;

    public IncreaseMemberMoneyCommand(Long targetMembershipId, Integer increaseMoneyAmount) {
        this.targetMembershipId = targetMembershipId;
        this.increaseMoneyAmount = increaseMoneyAmount;

        this.validateSelf();
    }
}
