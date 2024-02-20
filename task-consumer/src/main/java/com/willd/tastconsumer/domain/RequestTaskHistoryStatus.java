package com.willd.tastconsumer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RequestTaskHistoryStatus {
    REQUEST("요청"),
    SUCCESS("성공"),
    FAIL("실패"),
    ;
    private final String description;

}
