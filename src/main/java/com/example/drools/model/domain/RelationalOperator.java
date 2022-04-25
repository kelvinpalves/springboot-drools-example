package com.example.drools.model.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum RelationalOperator {
    EQUALS("=="),
    GREATER(">"),
    LESS("<"),
    GREATER_OR_EQUALS(">="),
    LESS_OR_EQUALS("<=");

    final String symbol;
}
