package com.example.drools.model.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Objective {

    String key;
    String value;
    DataType dataType;

}
