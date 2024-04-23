package com.alipay.altershield.shared.pluginmarket.innerplugin.defender;

import com.alibaba.fastjson.JSONObject;
import com.alipay.altershield.common.logger.Loggers;
import com.alipay.altershield.framework.common.util.logger.AlterShieldLoggerManager;
import com.alipay.altershield.spi.defender.DefenderAsyncDetectPlugin;
import com.alipay.altershield.spi.defender.model.enums.DefenderStatusEnum;
import com.alipay.altershield.spi.defender.model.request.DefenderDetectPluginRequest;
import com.alipay.altershield.spi.defender.model.result.DefenderDetectPluginResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yujianjia
 * @since 2024/4/23
 */
public class AsyncDetectDemoPlugin implements DefenderAsyncDetectPlugin {

    private static Map<String,Long> timeMap = new ConcurrentHashMap<>();
    @Override
    public DefenderDetectPluginResult submitDetectTask(DefenderDetectPluginRequest request) {
        timeMap.put(request.getNodeId(),System.currentTimeMillis());
        AlterShieldLoggerManager.log("info", Loggers.DEFENDER_PLUGIN, "AsyncDetectDemoPlugin",
                "submitDetectTask", request);
        return DefenderDetectPluginResult.success();
    }

    @Override
    public DefenderDetectPluginResult retrieveDetectResult(DefenderDetectPluginRequest request) {
        long startTime = timeMap.get(request.getNodeId());
        if (System.currentTimeMillis() - startTime < 1000 * 5) {
            AlterShieldLoggerManager.log("info", Loggers.DEFENDER_PLUGIN, "AsyncDetectDemoPlugin",
                    "retrieveDetectResult", "not ready ", request.getNodeId());
            return DefenderDetectPluginResult.success(false);
        }
        DefenderStatusEnum status = DefenderStatusEnum.PASS;
        String message = "";
        if (request.getChangeKey().contains("fail")) {
            status = DefenderStatusEnum.FAIL;
            message = "change key contains fail";
        } else if (request.getParamJson() != null) {
            JSONObject jsonObject = JSONObject.parseObject(request.getParamJson());
            String dataStatus = jsonObject.getString("status");
            if ("failed".equals(dataStatus)) {
                status = DefenderStatusEnum.FAIL;
                message = "param status is failed";
            }
        }
        DefenderDetectPluginResult rst = new DefenderDetectPluginResult();
        rst.setDefensed(true);
        rst.setDetectGroupId(request.getDetectGroupId());
        rst.setStatus(status);
        rst.setMsg(message);
        rst.setResultJson("{}");
        rst.setDefenseFinished(true);
        return rst;
    }
}
