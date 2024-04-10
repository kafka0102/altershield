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
/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.alipay.altershield.shared.defender.model;

import com.alipay.altershield.shared.defender.enums.LogicalOperatorEnum;
import com.alipay.altershield.shared.defender.request.ChangeFilterRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * the filter condition structure
 *
 * @author yuefan.wyf
 * @version $Id: FilterCondition.java, v 0.1 2019年12月17日 下午9:17 yuefan.wyf Exp $
 */
public class FilterCondition {

    /**
     * logical opreator
     */
    private LogicalOperatorEnum logicalOperator = LogicalOperatorEnum.OR;

    /**
     * filter items list
     */
    private List<FilterItemCondition> itemConditions;

    /**
     * Filter match
     *
     * @param request Match request structure
     * @return Does it match
     */
    public boolean isMatch(ChangeFilterRequest request) {
        return isMatch(request, false);
    }

    /**
     * Filter match
     *
     * @param request Match request structure
     * @return Does it match
     */
    public boolean isMatch(ChangeFilterRequest request, boolean ignoreMatchScope) {
        // default return matched
        if (CollectionUtils.isEmpty(itemConditions)) {
            return true;
        }

        boolean match = LogicalOperatorEnum.AND.equals(logicalOperator);

        for (FilterItemCondition condition : itemConditions) {
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
     * Gets get item conditions.
     *
     * @return the get item conditions
     */
    public List<FilterItemCondition> getItemConditions() {
        return itemConditions;
    }

    /**
     * Sets set item conditions.
     *
     * @param itemConditions the item conditions
     */
    public void setItemConditions(List<FilterItemCondition> itemConditions) {
        this.itemConditions = itemConditions;
    }
}