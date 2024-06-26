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
import com.alipay.altershield.framework.core.change.model.AlterShieldChangeContent;
import com.alipay.altershield.framework.core.change.model.enums.ChangePhaseLastBatchEnum;
import com.alipay.altershield.shared.change.exe.node.entity.ExeBatchNodeEntity;
import com.alipay.altershield.shared.change.exe.node.entity.ExeChangeBatchEffectiveInfoEntity;
import com.alipay.altershield.shared.change.exe.node.entity.ExeNodeEntity;
import com.alipay.altershield.shared.change.exe.order.entity.ExeChangeOrderEntity;
import com.alipay.altershield.shared.change.exe.service.ExeChangeNodeService;
import com.alipay.altershield.shared.change.exe.service.ExeChangeOrderQueryService;
import com.alipay.altershield.shared.defender.DefenderTaskExecutor;
import com.alipay.altershield.shared.defender.request.DefenderTaskExecuteRequest;
import com.alipay.altershield.shared.defender.result.DefenderTaskResult;
import com.alipay.altershield.shared.schedule.enums.AlterShieldScheduleEventResultStatus;
import com.alipay.altershield.shared.schedule.event.defender.DefenderDetectEvent;
import com.alipay.altershield.shared.schedule.event.listener.AlterShieldSchedulerEventContext;
import com.alipay.altershield.shared.schedule.event.listener.AlterShieldSchedulerEventListener;
import com.alipay.altershield.shared.schedule.event.result.AlterShieldSchedulerEventExecuteResult;
import com.alipay.altershield.spi.defender.model.request.ChangeBaseInfo;
import com.alipay.altershield.spi.defender.model.request.ChangeContent;
import com.alipay.altershield.spi.defender.model.request.ChangeExecuteInfo;
import com.alipay.altershield.spi.defender.model.request.ChangeInfluenceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Change Defense Detection Event Listener
 *
 * @author yhaoxuan
 * @version DefenderDetectEventListener.java, v 0.1 2022年08月30日 3:19 下午 yhaoxuan
 */
@Component("defenderDetectEventListener")
public class DefenderDetectEventListener implements AlterShieldSchedulerEventListener<DefenderDetectEvent> {

    @Autowired
    private DefenderTaskExecutor defenderTaskExecutor;

    @Autowired
    private ExeChangeNodeService exeChangeNodeService;

    @Autowired
    private ExeChangeOrderQueryService exeChangeOrderQueryService;

    /**
     * Receive change defense detection event and start detection logic
     *
     * @param context event context
     * @param event event object
     * @return event result
     */
    @Override
    public AlterShieldSchedulerEventExecuteResult onEvent(AlterShieldSchedulerEventContext context, DefenderDetectEvent event) {
        AlterShieldLoggerManager.log("info", Loggers.DEFENDER, "DefenderDetectEventListener", "onEvent", event);
        AlterShieldSchedulerEventExecuteResult result = new AlterShieldSchedulerEventExecuteResult();
        DefenderTaskExecuteRequest request = buildTaskExecuteRequest(event);
        DefenderTaskResult executeRst = defenderTaskExecutor.execute(request);
        result.setMsg(executeRst.getMsg());
        if (executeRst.isNeedRetry()) {
            result.setStatus(AlterShieldScheduleEventResultStatus.RETRY);
            return result;
        }
        result.setStatus(AlterShieldScheduleEventResultStatus.SUCCESS);
        return result;
    }

    private DefenderTaskExecuteRequest buildTaskExecuteRequest(DefenderDetectEvent event) {
        DefenderTaskExecuteRequest request = new DefenderTaskExecuteRequest();
        request.setChangeSceneKey(event.getChangeSceneKey());
        request.setChangeKey(event.getChangeKey());
        request.setPluginKey(event.getPluginKey());
        request.setMainClass(event.getMainClass());

        request.setChangeOrderId(event.getChangeOrderId());
        request.setNodeId(event.getNodeId());
        request.setDetectGroupId(event.getDetectGroupId());
        request.setDetectExeId(event.getDetectExeId());
        request.setRuleId(event.getRuleId());
        request.setExternalId(event.getExternalId());
        request.setDefenseStage(event.getStage());

        ExeNodeEntity node = exeChangeNodeService.getNode(event.getNodeId());
        ExeChangeOrderEntity changeOrder = exeChangeOrderQueryService.getChangeOrder(event.getChangeOrderId());
        request.setChangeBaseInfo(buildChangeBaseInfo(changeOrder));
        request.setChangeExecuteInfo(buildChangExecuteInfo(node, event));
        request.setChangeInfluenceInfo(buildChangeInfluenceInfo(node, changeOrder));

        return request;
    }

    private ChangeInfluenceInfo buildChangeInfluenceInfo(ExeNodeEntity node, ExeChangeOrderEntity changeOrder) {
        ChangeInfluenceInfo changeInfluenceInfo = new ChangeInfluenceInfo();
        changeInfluenceInfo.setApps(changeOrder.getChangeAppsRef().readObject());
        if (!Objects.isNull(node.getSearchExtRef())) {
            changeInfluenceInfo.setExtInfo(node.getSearchExtRef().readObject());
        }

        return changeInfluenceInfo;
    }

    private ChangeExecuteInfo buildChangExecuteInfo(ExeNodeEntity exeNode, DefenderDetectEvent event) {

        ChangeExecuteInfo changeExecuteInfo = new ChangeExecuteInfo();
        if (exeNode instanceof ExeBatchNodeEntity) {
            ExeChangeBatchEffectiveInfoEntity effectiveInfoEntity = ((ExeBatchNodeEntity) exeNode).getChangeEffectiveInfoRef().readObject();
            changeExecuteInfo.setBatchNo(effectiveInfoEntity.getBatchNo());
            changeExecuteInfo.setLastBatch(
                    ChangePhaseLastBatchEnum.LAST.getTag().equals(effectiveInfoEntity.getIsLastBatchTag()));
            changeExecuteInfo.setLastBatchInPhase(
                    ChangePhaseLastBatchEnum.LAST.getTag().equals(effectiveInfoEntity.getIsLastBatchInPhaseTag()));
            changeExecuteInfo.setTotalBatchNum(effectiveInfoEntity.getTotalBatchNum());
            changeExecuteInfo.setTotalBatchInPhase(effectiveInfoEntity.getTotalBatchInEnvNum());
            changeExecuteInfo.setExtInfo(effectiveInfoEntity.getExtInfo());
        }
        changeExecuteInfo.setChangeStartTime(event.getChangeStartTime());
        if (event.getChangeFinishTime() != null) {
            changeExecuteInfo.setChangeFinishTime(event.getChangeFinishTime());
        }
        changeExecuteInfo.setOrderPhase(event.isOrderPhase());
        return changeExecuteInfo;
    }

    private ChangeBaseInfo buildChangeBaseInfo(ExeChangeOrderEntity changeOrder) {
        ChangeBaseInfo changeBaseInfo = new ChangeBaseInfo();
        changeBaseInfo.setChangeTitle(changeOrder.getChangeTitle());
        changeBaseInfo.setCreator(changeOrder.getCreator());
        changeBaseInfo.setExecutor(changeOrder.getExecutor());
        changeBaseInfo.setPlatform(changeOrder.getPlatform());
        changeBaseInfo.setChangeParamJSON(changeOrder.getParamRef().readObject());

        ChangeContent changeContent = new ChangeContent();
        List<AlterShieldChangeContent> contents = changeOrder.getChangeContentRef().readObject();
        if (!CollectionUtils.isEmpty(contents)) {
            changeContent.setChangeContentType(contents.get(0).getContentType().getTypeName());
            changeContent.setChangeContentInstance(contents.stream().map(AlterShieldChangeContent::getInstanceName)
                    .collect(Collectors.toList()));
        }
        changeBaseInfo.setChangeContent(changeContent);

        return changeBaseInfo;
    }
}