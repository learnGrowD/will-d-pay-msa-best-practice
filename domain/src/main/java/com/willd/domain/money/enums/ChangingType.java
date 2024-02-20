package com.willd.domain.money.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ChangingType {
    INCREASE("증액"),
    DECREASE("감액"),
    ;

    private final String description;
}
