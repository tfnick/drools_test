package com.tfnick.demo.test;


import com.sample.GrAgendaEventListener;
import com.sample.GrProcessEventListener;
import com.sample.GrRuleRuntimeEventListener;
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
		model.setAge(17);
		model.setCount(6);

		params.put("m", model);

		ksession.addEventListener(new GrRuleRuntimeEventListener());
		
		ksession.addEventListener(new GrAgendaEventListener());
		
		ksession.addEventListener(new GrProcessEventListener());
		
		ksession.insert(model);
		
		ksession.startProcess("com.sample.bpmn",null);

		int hit = ksession.fireAllRules();

		System.out.println("hit: " + hit);

		System.out.println(model.getAdvice());
		System.out.println(model.getReason());


		ksession.dispose();
	}

}
