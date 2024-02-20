package com.willd.domain.money.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MoneyChargingRequestStatus {
    REQUEST("요청"),
    SUCCESS("성공"),
    FAIL("실패"),
    ;
    private final String description;
}
