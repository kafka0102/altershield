package com.alipay.altershield.framework.core.change.ark.request;

import java.util.List;
import java.util.Map;

/**
 * @author yujianjia
 * @since 2024/5/13
 */
class ChangeStartRequest {
    String changeSceneKey;
    String bizId;
    String changeTitle;
    String executor;
    String env;
    List<String> changePhases;
    List<String>  changeTargets;
    List<String>  influenceInstances;
    // json string
    String changeParam;
    Map<String, Object> extInfo;
    // 紧急变更可忽略防御检查
    Boolean emergency;
    // 有审批 ID 能通过审批检查
    String  auditId;
    // 本次变更如果是回滚，可以带上要回滚的变更任务 ID
    String  rollbackTaskId;
    long startTime;
}
