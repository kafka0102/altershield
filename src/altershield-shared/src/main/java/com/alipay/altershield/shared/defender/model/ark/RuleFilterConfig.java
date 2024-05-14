package com.alipay.altershield.shared.defender.model.ark;

import java.util.List;

/**
 * @author yujianjia
 * @since 2024/5/10
 */
public class RuleFilterConfig {
    private LogicalOperator logicalOperator = LogicalOperator.OR;
    private List<FilterConditionGroup> filterConditionGroups;
}
