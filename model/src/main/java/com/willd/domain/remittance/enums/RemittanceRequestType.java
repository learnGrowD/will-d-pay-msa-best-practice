package com.willd.domain.remittance.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RemittanceRequestType {
    MEMBERSHIP("내부 고객"),
    BANK("외부 고객"),
    ;
    private final String description;
}
