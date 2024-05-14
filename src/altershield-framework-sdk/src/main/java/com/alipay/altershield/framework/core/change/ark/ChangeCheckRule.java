package com.alipay.altershield.framework.core.change.ark;

/**
 * @author yujianjia
 * @since 2024/5/13
 */
public class ChangeCheckRule {
    String ruleExecId;
    String resultJson;
    // SUCCESS,FAIL,TIMEOUT
    String status;
    // PASS, FAIL
    String verdict;
    String message;
    // PRE,POST
    String stage;
    String name;
    boolean ignored;
    long startTime;
    long finishTime;
}
