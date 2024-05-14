package com.alipay.altershield.framework.core.change.ark.request;

import java.util.Map;

/**
 * @author yujianjia
 * @since 2024/5/13
 */
class ChangeOrderFinishRequest {
    String orderId;
    boolean success;
    String message;
    String changeResultJson;
    Map<String, Object> extInfo;
    long finishTime;
}
