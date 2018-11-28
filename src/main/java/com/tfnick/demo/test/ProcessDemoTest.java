package com.tfnick.demo.test;

import com.sample.GrAgendaEventListener;
import com.sample.HelloProcessModel;
import com.tfnick.demo.facts.PersonModel;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.HashMap;
import java.util.Map;

public class ProcessDemoTest {

    public static void main(String[] args) {
        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession ksession = kContainer.newKieSession("ksession-ruleflow");
            ksession.addEventListener(new GrAgendaEventListener());

            // set the parameters
            Map<String, Object> params = new HashMap<String, Object>();
            PersonModel model = new PersonModel();


            model.setCount(3);
            model.setProvince("BJ");
            model.setAge(17);

            params.put("m", model);
            //ksession.insert(model);
            ksession.startProcess("ruleflow-demo",params);


            int hit = ksession.fireAllRules();

            System.out.println(hit);

            System.out.println(model.getAdvice());
            System.out.println(model.getReason());

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
