package com.example.drools;

import com.example.drools.model.CustomerType;
import com.example.drools.model.OrderDiscount;
import com.example.drools.model.OrderRequest;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DroolsTestApplicationTests {

    @Autowired
    private KieContainer kieContainer;

    @Test
    void validate() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomerNumber("1235");
        orderRequest.setAge(18);
        orderRequest.setAmount(15);
        orderRequest.setCustomerType(CustomerType.LOYAL);

        OrderDiscount orderDiscount = new OrderDiscount();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("orderDiscount", orderDiscount);
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();

        kieSession.dispose();
        System.out.println(orderDiscount.getDiscount());
    }



}
