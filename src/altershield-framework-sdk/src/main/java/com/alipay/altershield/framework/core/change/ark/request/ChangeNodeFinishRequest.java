package com.alipay.altershield.framework.core.change.ark.request;


import java.util.Map;

/**
 * @author yujianjia
 * @since 2024/5/13
 */
class ChangeNodeFinishRequest {
    String nodeId;
    String finishState; // SUCCESS, ROLLBACK, CANCEL
    // json string
    String changeResult;
    Map<String, Object> extInfo;
    long finishTime;
}
