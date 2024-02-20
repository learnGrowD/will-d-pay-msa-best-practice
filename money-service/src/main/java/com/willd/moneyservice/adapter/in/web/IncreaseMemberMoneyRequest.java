package com.willd.moneyservice.adapter.in.web;

import lombok.Getter;

@Getter
public class IncreaseMemberMoneyRequest {
    private Long targetMembershipId;

    private Integer increaseMoneyAmount;
}
