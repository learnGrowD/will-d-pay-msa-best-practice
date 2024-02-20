package com.willd.domain.remittance.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RemittanceRequestStatus {
    REQUEST("요청"),
    SUCCESS("성공"),
    FAIL("실패"),
    ;
    private final String description;
}
