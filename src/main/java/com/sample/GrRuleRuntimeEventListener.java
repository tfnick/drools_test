package com.sample;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 事实属性更新事件监听
 */

public class GrRuleRuntimeEventListener implements RuleRuntimeEventListener {

    public GrRuleRuntimeEventListener() {
    }

    private static final Logger logger = LoggerFactory.getLogger(GrRuleRuntimeEventListener.class);

    @Override
    public void objectInserted(ObjectInsertedEvent event) {

        logger.info(event.toString());
    }

    @Override
    public void objectUpdated(ObjectUpdatedEvent event) {

        logger.info(event.toString());
    }

    @Override
    public void objectDeleted(ObjectDeletedEvent event) {

        logger.info(event.toString());
    }
}
