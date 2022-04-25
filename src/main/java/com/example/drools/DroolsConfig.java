package com.example.drools;

import com.example.drools.model.DroolsConfigException;
import java.io.StringReader;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;

@Slf4j
public class DroolsConfig {

    private static final KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    private static final KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();

    private DroolsConfig() {}

    public static KieSession initDrools(String rule){
        final var resource = ResourceFactory.newReaderResource(new StringReader(rule));
        knowledgeBuilder.add(resource, ResourceType.DRL);

        if ( knowledgeBuilder.hasErrors() ) {
            log.error(knowledgeBuilder.getErrors().toString());
            System.out.println(knowledgeBuilder.getErrors());
            throw new DroolsConfigException( "Unable to compile drl");
        }

        Collection<KnowledgePackage> knowledgePackages = knowledgeBuilder.getKnowledgePackages();
        knowledgeBase.addKnowledgePackages(knowledgePackages);

        return knowledgeBase.newStatefulKnowledgeSession();
    }
}
