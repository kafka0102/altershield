package com.alipay.altershield.shared.defender.model.ark;

/**
 * @author yujianjia
 * @since 2024/5/10
 */
public enum LogicalOperator {

    /**
     * and
     */
    AND("AND"),

    /**
     * or
     */
    OR("OR");

    /**
     * operator
     */
    private final String operator;

    private LogicalOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Getter method for property <tt>operator</tt>.
     *
     * @return property value of operator
     */
    public String getOperator() {
        return operator;
    }
}
