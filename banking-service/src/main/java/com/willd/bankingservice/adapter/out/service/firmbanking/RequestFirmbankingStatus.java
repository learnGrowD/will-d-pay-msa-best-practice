package com.willd.bankingservice.adapter.out.service.firmbanking;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RequestFirmbankingStatus {
    SUCCESS("성공"),
    FAIL("실패"),
    ;
    private final String description;
}
