package com.example.drools.model.global;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Result {

    List<String> rulesExecuted = new ArrayList<>();

}
