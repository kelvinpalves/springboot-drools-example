package com.example.drools.model.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class Result {

    List<String> rulesExecuted = new ArrayList<>();
    Map<String, Object> data = new HashMap<>();

    public void addData(String key, Object value) {
        data.put(key, value);
    }

}
