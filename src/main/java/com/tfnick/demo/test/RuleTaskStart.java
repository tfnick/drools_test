package com.tfnick.demo.test;


import com.tfnick.demo.facts.PersonModel;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.HashMap;
import java.util.Map;

public class RuleTaskStart extends QuickStartBase {

	public static void main(String[] args) {
		new RuleTaskStart().test();
	}

	public void test() {
		//StatefulKnowledgeSession ksession = createKnowledgeSessionWithDrl("quickstarts/ruletaskprocess-rule.drl", "quickstarts/ruletaskprocess.bpmn");
		//ksession.startProcess("org.jbpm.quickstarts.ruletaskprocess");

		StatefulKnowledgeSession ksession = createKnowledgeSessionWithDrl("ruleflow/demo_rules1.drl", "ruleflow/demo_ruleflow.bpmn");

		Map<String, Object> params = new HashMap<String, Object>();
		PersonModel model = new PersonModel();


		model.setProvince("BJ");
		model.setAge(35);
		model.setCount(6);

		params.put("m", model);


		//ksession.insert(model);
		ksession.startProcess("ruleflow-demo",params);

		int hit = ksession.fireAllRules();

		System.out.println("hit: " + hit);

		System.out.println(model.getAdvice());
		System.out.println(model.getReason());


		ksession.dispose();
	}

}
