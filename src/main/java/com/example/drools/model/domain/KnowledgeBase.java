package com.example.drools.model.domain;

import java.util.List;
import lombok.Data;

@Data
public class KnowledgeBase {

    String code;
    String name;
    List<Rule> rules;

}

