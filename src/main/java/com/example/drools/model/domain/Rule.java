package com.example.drools.model.domain;

import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rule {
    Integer order;
    String code;
    String name;
    List<List<Condition>> conditions;
    List<Objective> objectives;
}
