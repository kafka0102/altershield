package com.alipay.altershield.framework.core.change.ark.request;

import com.alipay.altershield.framework.core.change.ark.ChangeObject;

import java.util.List;
import java.util.Map;

/**
 * @author yujianjia
 * @since 2024/5/13
 */
class ChangeStartRequest {
    String changeSceneKey;
    String bizId;
    String tenantId;
    String changeTitle;
    String executor;
    List<String> changePhases;
    List<ChangeObject>  changeTargets;
    List<ChangeObject>  influenceInstances;
    String changeParamJson;
    Map<String, Object> extInfo;
    long startTime;
}
