package com.alipay.altershield.framework.core.change.ark.request;

/**
 * @author yujianjia
 * @since 2024/5/13
 */
class ChangeBatchStartRequest {
    String taskId;
    String phaseName;
    int batchNo;
    int totalBatchNum;
    long startTime;
}
