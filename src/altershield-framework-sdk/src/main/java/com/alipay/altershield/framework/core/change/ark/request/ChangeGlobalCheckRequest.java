package com.alipay.altershield.framework.core.change.ark.request;

import com.alipay.altershield.framework.core.change.ark.ChangeObject;

import java.util.List;

/**
 * @author yujianjia
 * @since 2024/5/13
 */
class ChangeGlobalCheckRequest {
    String changeSceneKey;
    List<String> changePhases;
    List<ChangeObject>  changeTargets;
}
