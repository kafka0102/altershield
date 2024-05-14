package com.alipay.altershield.shared.defender.model.ark;

import java.util.List;

/**
 * @author yujianjia
 * @since 2024/5/10
 */
public class FilterConditionGroup {
    private LogicalOperator logicalOperator = LogicalOperator.OR;
    private List<FilterConditionItem> items;

    private List<FilterConditionGroup> children;
}
