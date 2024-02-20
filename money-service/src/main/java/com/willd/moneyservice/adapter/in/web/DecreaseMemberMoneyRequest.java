package com.willd.moneyservice.adapter.in.web;

import lombok.Getter;

@Getter
public class DecreaseMemberMoneyRequest {
    private Long targetMembershipId;

    private Integer decreaseMoneyAmount;
}
