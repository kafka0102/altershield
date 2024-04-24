package com.alipay.altershield.defender.framework.listener;

import com.alipay.altershield.common.logger.Loggers;
import com.alipay.altershield.framework.common.util.logger.AlterShieldLoggerManager;
import com.alipay.altershield.shared.schedule.event.defender.DefenderDetectEvent;
import com.alipay.altershield.shared.schedule.event.defender.DefenderDetectFinishEvent;
import com.alipay.altershield.shared.schedule.event.listener.AlterShieldSchedulerEventContext;
import com.alipay.altershield.shared.schedule.event.listener.AlterShieldSchedulerEventListener;
import com.alipay.altershield.shared.schedule.event.result.AlterShieldSchedulerEventExecuteResult;
import org.springframework.stereotype.Component;

/**
 * @author yujianjia
 * @since 2024/4/24
 */
@Component("DefenderDetectFinishEventListener")
public class DefenderDetectFinishEventListener implements AlterShieldSchedulerEventListener<DefenderDetectFinishEvent> {

    @Override
    public AlterShieldSchedulerEventExecuteResult onEvent(AlterShieldSchedulerEventContext context, DefenderDetectFinishEvent event) {
        AlterShieldLoggerManager.log("info", Loggers.DEFENDER, "DefenderDetectFinishEventListener", "onEvent", event);
        return AlterShieldSchedulerEventExecuteResult.success("Defense detection finish event successfully");
    }
}
