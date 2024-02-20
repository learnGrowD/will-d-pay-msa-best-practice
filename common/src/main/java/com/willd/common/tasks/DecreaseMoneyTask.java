package com.willd.common.tasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecreaseMoneyTask {
    private UUID uuid;

    private Long targetMembershipId;

    private Integer decreaseMoneyAmount;
}
