package com.example.drools;

import com.example.drools.model.RuleObject;
import com.example.drools.model.global.Result;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.util.AssertionErrors;
import org.springframework.util.FileCopyUtils;

@SpringBootTest
class DroolsTestApplicationTests {

    @Value("classpath:rules/example.drl")
    private Resource resource;

    private String getRules() {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Test
    void validateGenericObject() {
        for (int i = 0; i < 10; i++) {
            KieSession kieSession = DroolsConfig.initDrools(getRules());

            RuleObject ruleObject = new RuleObject();
            ruleObject.initData()
                    .addField("name", "Kelvin")
                    .addField("age", 20 + i);

            Result result = new Result();

            kieSession.setGlobal("result", result);

            System.out.println(ruleObject);

            kieSession.insert(ruleObject);
            kieSession.fireAllRules();
            kieSession.dispose();

            System.out.println(result);
            System.out.println("--");
        }
    }
}
