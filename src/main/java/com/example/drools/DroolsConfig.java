package com.example.drools;

import java.io.StringReader;
import java.util.Collection;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

    private final KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    private final KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();

    @Bean
    public KieSession initDrools(){
        final var rule = "import com.example.drools.model.*;\n" +
                "\n" +
                "dialect \"mvel\"\n" +
                "\n" +
                "rule \"Regra Nome\"\n" +
                "    when\n" +
                "        RuleObject(data['name'] == \"Kelvin\")\n" +
                "    then\n" +
                "        System.out.println(\"The name is Kelvin\");\n" +
                "end\n" +
                "\n" +
                "rule \"Regra Idade\"\n" +
                "    when\n" +
                "        RuleObject(data['age'] > 25)\n" +
                "    then\n" +
                "        System.out.println(\"The age is greater than 25\");\n" +
                "end";

        final var resource = ResourceFactory.newReaderResource(new StringReader(rule));
        knowledgeBuilder.add(resource, ResourceType.DRL);

        if ( knowledgeBuilder.hasErrors() ) {
            throw new RuntimeException( "Unable to compile drl\"." );
        }

        Collection<KnowledgePackage> knowledgePackages = knowledgeBuilder.getKnowledgePackages();
        knowledgeBase.addKnowledgePackages(knowledgePackages);

        return knowledgeBase.newStatefulKnowledgeSession();
    }
}
