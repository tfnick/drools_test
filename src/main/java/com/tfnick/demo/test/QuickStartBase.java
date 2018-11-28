package com.tfnick.demo.test;


import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public abstract class QuickStartBase {

	protected StatefulKnowledgeSession createKnowledgeSession(String... process) {
		KnowledgeBase kbase = createKnowledgeBase(process);
		return createKnowledgeSession(kbase);
	}
	
	protected KnowledgeBase createKnowledgeBase(String... process) {
		
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        
        for (String p: process) {
            kbuilder.add(ResourceFactory.newClassPathResource(p), ResourceType.BPMN2);
        }
        
        if (kbuilder.hasErrors()) {
            if (kbuilder.getErrors().size() > 0) {
                throw new RuntimeException("Create KnowledgeBuilder Error," + kbuilder.getErrors().toString());
            }
        }
        return kbuilder.newKnowledgeBase();
    }
	
	protected StatefulKnowledgeSession createKnowledgeSessionWithDrl(String drl,  String... process) {
		KnowledgeBase kbase = createKnowledgeBaseWithDrl(drl, process);
		return createKnowledgeSession(kbase);
	}
	
	protected KnowledgeBase createKnowledgeBaseWithDrl(String drl, String... process) {
		
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        
        for (String p: process) {
            kbuilder.add(ResourceFactory.newClassPathResource(p), ResourceType.BPMN2);
        }
        
        if(drl != null) {
        	kbuilder.add(ResourceFactory.newClassPathResource(drl), ResourceType.DRL);
        }
        
        if (kbuilder.hasErrors()) {
            if (kbuilder.getErrors().size() > 0) {
                throw new RuntimeException("Create KnowledgeBuilder Error," + kbuilder.getErrors().toString());
            }
        }
        return kbuilder.newKnowledgeBase();
    }
	
	protected StatefulKnowledgeSession createKnowledgeSession(KnowledgeBase kbase) {
		
	    StatefulKnowledgeSession result;
        KieSessionConfiguration conf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        
        result = kbase.newStatefulKnowledgeSession();
        
		return result;
	}
	
	public abstract void test();
}
