package com.example.drools.model.domain;

import lombok.Data;

@Data
public class Condition {

    String key;
    String value;
    RelationalOperator relationalOperator;
    DataType type;
    LogicalOperator logicalOperator;

}
