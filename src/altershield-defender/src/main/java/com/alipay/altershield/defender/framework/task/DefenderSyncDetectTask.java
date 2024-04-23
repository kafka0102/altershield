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
package com.alipay.altershield.defender.framework.task;


import com.alipay.altershield.common.id.enums.IdBizCodeEnum;
import com.alipay.altershield.common.logger.Loggers;
import com.alipay.altershield.common.util.DateUtil;
import com.alipay.altershield.defender.framework.AbstractDefenderService;
import com.alipay.altershield.defender.framework.meta.entity.MetaDefenderRuleEntity;
import com.alipay.altershield.framework.common.util.logger.AlterShieldLoggerManager;
import com.alipay.altershield.shared.defender.entity.ExeDefenderDetectEntity;
import com.alipay.altershield.shared.defender.request.DefenderDetectRequest;
import com.alipay.altershield.spi.defender.model.enums.DefenderStatusEnum;
import com.alipay.altershield.spi.defender.model.result.DefenderDetectPluginResult;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Synchronize defense execution tasks, each task corresponds to a rule
 *
 * @author yhaoxuan
 * @version DefenderSyncDetectTask.java, v 0.1 2022年12月09日 11:28 上午 yhaoxuan
 */
@Data
@Component
@Scope("prototype")
public class DefenderSyncDetectTask extends AbstractDefenderService implements Callable<DefenderDetectPluginResult> {

    /**
     * The rule entity for the task to be executed
     */
    private MetaDefenderRuleEntity rule;

    /**
     * Detection group id
     */
    private String detectGroupId;

    /**
     * Concurrency counter
     */
    private CountDownLatch countDownLatch;

    /**
     * Defense detection request structure
     */
    private DefenderDetectRequest request;

    public DefenderSyncDetectTask() {

    }

    public DefenderSyncDetectTask(MetaDefenderRuleEntity rule, String detectGroupId, CountDownLatch countDownLatch,
                                  DefenderDetectRequest request) {
        this.rule = rule;
        this.detectGroupId = detectGroupId;
        this.countDownLatch = countDownLatch;
        this.request = request;
    }

    @Override
    public DefenderDetectPluginResult call() throws Exception {
        AlterShieldLoggerManager.log("info", Loggers.DEFENDER, "DefenderSyncDetectTask", "call", "start to execute rule",
                request.getNodeId(), rule.getId());
        DefenderDetectPluginResult result = new DefenderDetectPluginResult();
        result.setRuleId(rule.getId());
        result.setDetectGroupId(detectGroupId);
        result.setStatus(DefenderStatusEnum.PASS);
        try {
            // 1.0 Generate the ID of the detection record
            String detectExeId = idGenerator.generateIdByRelatedId(IdBizCodeEnum.OPSCLD_EXE_DEFENDER_DETECT_EXE_ID, detectGroupId);
            result.setStatus(DefenderStatusEnum.PASS);
            result = executeSyncPlugin(buildDefenderTaskExecuteRequest(request, rule, detectGroupId, detectExeId));
        } catch (Throwable ex) {
            AlterShieldLoggerManager.log("error", Loggers.DEFENDER, "DefenderSyncDetectTask", "call",
                    "execute rule with exception", request.getNodeId(), rule.getId(), ex);
            result.setStatus(DefenderStatusEnum.EXCEPTION);
        }
        AlterShieldLoggerManager.log("info", Loggers.DEFENDER, "DefenderSyncDetectTask", "call", "done execute rule",
                result);
        // 此处实现不严谨
        try {
            // 2.0 Create a detection record asynchronously
            DefenderDetectPluginResult finalResult = result;
            ExeDefenderDetectEntity detectRecord = createDetectEntity(request.getChangeOrderId(), request.getNodeId(),
                    detectGroupId, request.getDefenseStage(), rule);
            switch (finalResult.getStatus()) {
                case PASS:
                    detectRecord.setStatus(DefenderStatusEnum.PASS);
                    detectRecord.setMsg("Check passed!");
                    break;
                case FAIL:
                    detectRecord.setStatus(DefenderStatusEnum.FAIL);
                    detectRecord.setMsg("Check failed!");
                    break;
                case TIMEOUT:
                    detectRecord.setStatus(DefenderStatusEnum.TIMEOUT);
                    detectRecord.setMsg("Check timeout!");
                    break;
                case EXCEPTION:
                default:
                    detectRecord.setStatus(DefenderStatusEnum.EXCEPTION);
                    detectRecord.setMsg("Check with an exception!");
            }
            detectRecord.setGmtFinish(DateUtil.getNowDate());
            // 这个异常也可能丢了
            exeDefenderDetectRepository.insert(detectRecord);
            AlterShieldLoggerManager.log("info", Loggers.DEFENDER, "DefenderSyncDetectTask", "call", "insert detectRecord",
                    detectRecord);
            // 计数不减外部调用一直是超时
        } catch (Throwable ex) {
            AlterShieldLoggerManager.log("error", Loggers.DEFENDER, "DefenderSyncDetectTask", "call",
                    "insert detectRecord with exception", request.getNodeId(), rule.getId(), ex);
            result.setStatus(DefenderStatusEnum.EXCEPTION);
        } finally {
            countDownLatch.countDown();
        }
        return result;
    }
}