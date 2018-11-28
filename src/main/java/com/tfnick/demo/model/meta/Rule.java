package com.tfnick.demo.model.meta;

import java.util.List;

public class Rule {
    /**
     * 全局唯一
     */
    String name;

    String comment;

    /**
     * 规则用到的metric，多个之间用,分割
     * 此属性是一个不稳定属性，每一次流程或者规则的变更会触发此属性的更新
     */
    String metrics;

    //启用|停用规则
    int status;
    //继续或终止流程
    int hitAction;

    //规则表达式
    String whenCondition;

    //业务决策输出
    String advice;
    String reason;

    /**
     * 生成单条规则内容
     * @return
     *
     * @refer https://blog.csdn.net/caicongyang/article/details/52702628
     */
    public String buildRuleByTemplate(){
        return null;
    }
}
