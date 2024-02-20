package com.willd.common.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskCategory {
    INCREASE_MONEY("money/increase"),
    DECREASE_MONEY("money/decrease");
    private final String domain;
}
