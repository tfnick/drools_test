package com.sample;

import org.drools.core.util.StringUtils;
import org.kie.api.event.rule.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 规则命中相关事件监听
 */
public class GrAgendaEventListener implements AgendaEventListener {

    private static Logger RULE_HIT_LOGGER = LoggerFactory.getLogger(GrAgendaEventListener.class);

    //规则编码必须符合规范 FF01#描述信息， 其中FF01是需要提取的规则编码
    private  RuleCodeResolve ruleCodeResolve = new RuleCodeResolve() {
        String split = "#";
        @Override
        public String resolve(String ruleName) {
            if (StringUtils.isEmpty(ruleName)) {
                return null;
            } else {
                if (ruleName.contains(split)) {
                    return ruleName.substring(0, ruleName.indexOf(split));
                } else {
                    return null;
                }
            }
        }
    };

    @Override
    public void matchCreated(MatchCreatedEvent event) {
        RULE_HIT_LOGGER.info("match rule {} matchCreated",event.getMatch().getRule().getName());
    }

    @Override
    public void matchCancelled(MatchCancelledEvent event) {
        RULE_HIT_LOGGER.info("match rule {} matchCancelled",event.getMatch().getRule().getName());
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        RULE_HIT_LOGGER.info("match rule {} beforeMatchFired",event.getMatch().getRule().getName());
    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        String ruleCode = ruleCodeResolve.resolve(event.getMatch().getRule().getName());
        RULE_HIT_LOGGER.info("rule {} matched and fired",ruleCode);
    }

    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
        RULE_HIT_LOGGER.info("match rule {} agendaGroupPopped",event.getAgendaGroup().getName());
    }

    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent event) {
        RULE_HIT_LOGGER.info("match rule {} agendaGroupPushed",event.getAgendaGroup().getName());
    }

    @Override
    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        RULE_HIT_LOGGER.info("match rule {} beforeRuleFlowGroupActivated",event.getRuleFlowGroup().getName());
    }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        RULE_HIT_LOGGER.info("match rule {} afterRuleFlowGroupActivated",event.getRuleFlowGroup().getName());
    }

    @Override
    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        RULE_HIT_LOGGER.info("match rule {} beforeRuleFlowGroupDeactivated",event.getRuleFlowGroup().getName());
    }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        RULE_HIT_LOGGER.info("match rule {} afterRuleFlowGroupDeactivated",event.getRuleFlowGroup().getName());
    }
}
