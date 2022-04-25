package com.example.drools;

import com.example.drools.model.domain.Condition;
import com.example.drools.model.domain.DataType;
import com.example.drools.model.domain.KnowledgeBase;
import com.example.drools.model.domain.LogicalOperator;
import com.example.drools.model.domain.Objective;
import com.example.drools.model.domain.Rule;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestController {

    @PostMapping("/convert")
    public String getObject(@RequestBody KnowledgeBase knowledgeBase) {

        StringBuilder builder = new StringBuilder();
        for (Rule rule : knowledgeBase.getRules()) {
            builder.append(printRule(rule));
        }

        return builder.toString();
    }

    public String printRule(Rule rule) {
        StringBuilder builder = new StringBuilder();
        builder.append("rule \"")
                .append(rule.getCode()).append(" - ")
                .append(rule.getName()).append("\"\n");
        builder.append("\twhen\n");
        builder.append("\t\tRuleObject(");

        Integer actualRule = 0;

        for (List<Condition> conditions : rule.getConditions()) {
            Integer actualChildRule = 0;
            builder.append(openGroupCondition(conditions.size()));

            for (Condition child : conditions) {
                builder.append(String.format("data['%s'] %s %s",
                        child.getKey(),
                        child.getRelationalOperator().getSymbol(),
                        getValue(child.getType(), child.getValue())));


                builder.append(logicalOperatorNeeded(actualChildRule++, conditions.size(), child.getLogicalOperator()));
            }

            builder.append(logicalOperatorNeeded(actualRule++, rule.getConditions().size(), conditions.get(0).getLogicalOperator()));

            builder.append(closeGroupCondition(conditions.size()));

        }
        builder.append(")").append("\n");
        builder.append("\tthen").append("\n");

        for (Objective objective : rule.getObjectives()) {
            builder.append(String.format("\t\tresult.addData(\"%s\", %s);\n",
                    objective.getKey(),
                    getValue(objective.getDataType(), objective.getValue())));
        }

        builder.append("end\n");

        return builder.toString();

        // ( ((1 || 2) && (1 || 2)) && 1 && (1 || 1))

    }

    private String logicalOperatorNeeded(Integer actualRule, Integer numberOfRules, LogicalOperator logicalOperator) {
        System.out.println(String.format("Actual: %d, Number of Rules: %d, Logical: %s", actualRule, numberOfRules, logicalOperator));
        return (actualRule + 1) < numberOfRules ? " " + logicalOperator.getSymbol() + " " : "";
    }

    private String openGroupCondition(Integer size) {
        return size > 1 ? "(" : "";
    }

    private String closeGroupCondition(Integer size) {
        return size > 1 ? ")" : "";
    }

    private String getValue(DataType type, String value) {
        if (DataType.NUMBER.equals(type)) {
            return value;
        } else {
            return String.format("\"%s\"", value);
        }
    }

}
