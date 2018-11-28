package com.sample;

import com.tfnick.demo.facts.PersonModel;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.*;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.manager.context.EmptyContext;

import java.util.HashMap;
import java.util.Map;

public class RuntimeManagerUtil {

    public static void main(String[] args) {
        // first configure environment that will be used by RuntimeManager

        RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory.get()

                .newEmptyBuilder()

                .addAsset(ResourceFactory.newClassPathResource("ruleflow/demo_ruleflow.bpmn"), ResourceType.BPMN2)
                .addAsset(ResourceFactory.newClassPathResource("ruleflow/demo_rules1.drl"), ResourceType.DRL)
                .addAsset(ResourceFactory.newClassPathResource("ruleflow/demo_rules2.drl"), ResourceType.DRL)

                .get();


        // next create RuntimeManager - in this case singleton strategy is chosen

        RuntimeManager manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);


        // then get RuntimeEngine out of manager - using empty context as singleton does not keep track

        // of runtime engine as there is only one

        RuntimeEngine runtimeEngine = manager.getRuntimeEngine(EmptyContext.get());



        // get KieSession from runtime runtimeEngine - already initialized with all handlers, listeners, etc that were configured

        // on the environment

        KieSession ksession = runtimeEngine.getKieSession();


        // add invocations to the process engine here,
        Map<String, Object> params = new HashMap<String, Object>();
        PersonModel model = new PersonModel();
        model.setCount(new Integer("3"));

        model.setProvince("北京");
        model.setAge(31);
        params.put("m", model);


        ksession.startProcess("ruleflow-demo",params);

        int hit = ksession.fireAllRules();

        System.out.println(hit);
        System.out.println(model.getAdvice());

        // and last dispose the runtime engine

        manager.disposeRuntimeEngine(runtimeEngine);
    }
}
