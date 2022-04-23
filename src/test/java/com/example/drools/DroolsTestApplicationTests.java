package com.example.drools;

import com.example.drools.model.RuleObject;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DroolsTestApplicationTests {

    @Autowired
    private KieSession kieSession;

    @Test
    void validateGenericObject() {

        for (int i = 0; i < 10; i++) {
            RuleObject ruleObject = new RuleObject();
            ruleObject.initData()
                    .addField("name", "Kelvin")
                    .addField("age", 20 + i);

            kieSession.insert(ruleObject);
            kieSession.fireAllRules();
            System.out.println("-");
        }

        kieSession.dispose();
    }
}
