package com.tfnick.demo.model.meta;

import java.util.List;

public class RuleSet {

    /**
     * 全局唯一
     */
    String name;

    String comment;

    List<Rule> ruleList;

    /**
     * 生成规则集的drl内容
     * @return
     */
    public String buildRuleSetByTemplate(){
        return null;
    }

    /**
     * 计算规则集关联的指标数
     * @return
     */
    public List<String> calculateMetrics(){
        return null;
    }
}
