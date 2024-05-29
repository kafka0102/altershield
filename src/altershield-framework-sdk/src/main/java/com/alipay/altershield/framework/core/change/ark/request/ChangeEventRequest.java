package com.alipay.altershield.framework.core.change.ark.request;

import com.alipay.altershield.framework.core.change.ark.ChangeObject;

import java.util.List;
import java.util.Map;

/**
 * @author yujianjia
 * @since 2024/5/13
 */
class ChangeEventRequest{
    String changeSceneKey;
    String bizId;
    String changeTitle;
    String executor;
    String env;
    List<String>  changeTargets;
    String changeParam;
    Map<String, Object> extInfo;
    long startTime;
    long finishTime;
}
