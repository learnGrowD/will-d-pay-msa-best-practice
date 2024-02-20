package com.willd.common.tasks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseMoneyTask {
    private UUID uuid;

    private Long targetMembershipId;

    private Integer increaseMoneyAmount;
}
