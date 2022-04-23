package com.example.drools.model;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RuleObject {

    Map<String, Object> data;

    public RuleObject initData() {
        this.data = new HashMap<>();
        return this;
    }

    public RuleObject addField(String key, Object data) {
        this.data.put(key, data);
        return this;
    }

}
