/*
 * MIT License
 *
 * Copyright (c) [2023]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.altershield.shared.defender.model;

import com.alipay.altershield.shared.defender.enums.LogicalOperatorEnum;
import com.alipay.altershield.shared.defender.request.ChangeFilterRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * the change filter structure
 *
 * @author yuefan.wyf
 * @version $Id: Filter.java, v 0.1 2019年12月17日 下午10:54 yuefan.wyf Exp $
 */
public class ChangeFilter {

    /**
     * Logical Operators
     */
    private LogicalOperatorEnum logicalOperator = LogicalOperatorEnum.OR;

    /**
     * Conditions
     */
    private List<FilterCondition> conditions;

    /**
     * Defense rule pre-matching
     *
     * @param request Match request structure
     * @return Whether the filter conditions are matched successfully
     */
    public boolean preMatch(ChangeFilterRequest request) {
        return match(request, true);
    }

    /**
     * Filter match
     *
     * @param request Match request structure
     * @return Whether the filter conditions are matched successfully
     */
    public boolean isMatch(ChangeFilterRequest request) {
        return match(request, false);
    }

    /**
     * Filter match
     *
     * @param request Match request structure
     * @param ignoreMatchScope Whether to ignore change scope matching
     * @return Whether the filter conditions are matched successfully
     */
    private boolean match(ChangeFilterRequest request, boolean ignoreMatchScope) {
        // default return matched
        if (CollectionUtils.isEmpty(conditions)) {
            return true;
        }

        boolean match = LogicalOperatorEnum.AND.equals(logicalOperator);

        for (FilterCondition condition : conditions) {
            if (LogicalOperatorEnum.AND.equals(logicalOperator)) {
                if (!match) {
                    return match;
                }
                match = match && condition.isMatch(request, ignoreMatchScope);
            } else {
                if (match) {
                    return match;
                }
                match = match || condition.isMatch(request, ignoreMatchScope);
            }
        }

        return match;
    }

    /**
     * Gets get logical operator.
     *
     * @return the get logical operator
     */
    public LogicalOperatorEnum getLogicalOperator() {
        return logicalOperator;
    }

    /**
     * Sets set logical operator.
     *
     * @param logicalOperator the logical operator
     */
    public void setLogicalOperator(LogicalOperatorEnum logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    /**
     * Gets get conditions.
     *
     * @return the get conditions
     */
    public List<FilterCondition> getConditions() {
        return conditions;
    }

    /**
     * Sets set conditions.
     *
     * @param conditions the conditions
     */
    public void setConditions(List<FilterCondition> conditions) {
        this.conditions = conditions;
    }
}