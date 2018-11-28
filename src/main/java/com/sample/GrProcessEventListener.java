package com.sample;

import org.drools.core.event.ProcessEvent;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrProcessEventListener implements ProcessEventListener {

    private static final Logger logger = LoggerFactory.getLogger(GrProcessEventListener.class);

	@Override
	public void beforeProcessStarted(ProcessStartedEvent event) {
		// TODO Auto-generated method stub
		logger.info(event.getProcessInstance().getProcessName());
		
		
	}

	@Override
	public void afterProcessStarted(ProcessStartedEvent event) {
		// TODO Auto-generated method stub
		logger.info(event.getProcessInstance().getProcessName());
	}

	@Override
	public void beforeProcessCompleted(ProcessCompletedEvent event) {
		// TODO Auto-generated method stub
		logger.info(event.getProcessInstance().getProcessName());
	}

	@Override
	public void afterProcessCompleted(ProcessCompletedEvent event) {
		// TODO Auto-generated method stub
		logger.info(event.getProcessInstance().getProcessName());
		
	}

	@Override
	public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
		// TODO Auto-generated method stub
		logger.info("before trigger Node : " +event.getNodeInstance().getNodeName());
	}

	@Override
	public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
		// TODO Auto-generated method stub
		logger.info(event.getProcessInstance().getProcessName());
	}

	@Override
	public void beforeNodeLeft(ProcessNodeLeftEvent event) {
		// TODO Auto-generated method stub
		logger.info(event.getProcessInstance().getProcessName());
	}

	@Override
	public void afterNodeLeft(ProcessNodeLeftEvent event) {
		// TODO Auto-generated method stub
		logger.info("left " + event.getNodeInstance().getNodeName());
	}

	@Override
	public void beforeVariableChanged(ProcessVariableChangedEvent event) {
		// TODO Auto-generated method stub
		logger.info(event.getProcessInstance().getProcessName());
	}

	@Override
	public void afterVariableChanged(ProcessVariableChangedEvent event) {
		// TODO Auto-generated method stub
		logger.info(event.getProcessInstance().getProcessName());
	}

}
