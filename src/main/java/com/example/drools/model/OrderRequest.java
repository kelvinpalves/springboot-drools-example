package com.example.drools.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {

    String customerNumber;
    Integer age;
    Integer amount;
    CustomerType customerType;

}
