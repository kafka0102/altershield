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
package com.alipay.altershield.defender.framework.listener;

import com.alipay.altershield.common.logger.Loggers;
import com.alipay.altershield.framework.common.util.logger.AlterShieldLoggerManager;
import com.alipay.altershield.shared.defender.DefenderTaskChecker;
import com.alipay.altershield.shared.defender.result.DefenderTaskResult;
import com.alipay.altershield.shared.schedule.enums.AlterShieldScheduleEventResultStatus;
import com.alipay.altershield.shared.schedule.event.defender.DefenderCheckProcessEvent;
import com.alipay.altershield.shared.schedule.event.listener.AlterShieldSchedulerEventContext;
import com.alipay.altershield.shared.schedule.event.listener.AlterShieldSchedulerEventListener;
import com.alipay.altershield.shared.schedule.event.result.AlterShieldSchedulerEventExecuteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Change Defense Batch Detection Task Status Checker
 *
 * @author yhaoxuan
 * @version DefenderCheckProcessEventListener.java, v 0.1 2022年08月30日 4:01 下午 yhaoxuan
 */
@Component("defenderCheckProcessEventListener")
public class DefenderCheckProcessEventListener implements AlterShieldSchedulerEventListener<DefenderCheckProcessEvent> {

    @Autowired
    private DefenderTaskChecker defenderTaskChecker;

    /**
     * Receive change defense batch detection task status check events and summarize the status of all detection tasks in the batch
     *
     * @param context event context
     * @param event event object
     * @return event result
     */
    @Override
    public AlterShieldSchedulerEventExecuteResult onEvent(AlterShieldSchedulerEventContext context, DefenderCheckProcessEvent event) {
        AlterShieldLoggerManager.log("info", Loggers.DEFENDER, "DefenderCheckProcessEventListener", "onEvent",
                event.getChangeOrderId(), event.getNodeId(), event.getStage().getStage());

        AlterShieldSchedulerEventExecuteResult result = new AlterShieldSchedulerEventExecuteResult();

        DefenderTaskResult executeRst = defenderTaskChecker.checkDetectProcess(event.getChangeOrderId(),
                event.getNodeId(), event.getDetectGroupId(), event.getStage(), event.getChangeSceneKey(), event.getChangeStepType());
        result.setMsg(executeRst.getMsg());
        if (executeRst.isNeedRetry()) {
            result.setStatus(AlterShieldScheduleEventResultStatus.RETRY);
        } else {
            result.setStatus(AlterShieldScheduleEventResultStatus.SUCCESS);
        }
        return result;
    }
}