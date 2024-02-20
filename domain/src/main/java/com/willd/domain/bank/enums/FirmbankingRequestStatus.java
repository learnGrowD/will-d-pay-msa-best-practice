package com.willd.domain.bank.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FirmbankingRequestStatus {
    REQUEST("요청"),
    SUCCESS("성공"),
    FAIL("실패"),
    ;
    private final String description;
}
