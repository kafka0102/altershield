package com.alipay.altershield.shared.pluginmarket.innerplugin.defender;

import com.alibaba.fastjson.JSONObject;
import com.alipay.altershield.common.logger.Loggers;
import com.alipay.altershield.framework.common.util.logger.AlterShieldLoggerManager;
import com.alipay.altershield.spi.defender.DefenderAsyncDetectPlugin;
import com.alipay.altershield.spi.defender.model.enums.DefenderStatusEnum;
import com.alipay.altershield.spi.defender.model.request.DefenderDetectPluginRequest;
import com.alipay.altershield.spi.defender.model.result.DefenderDetectPluginResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yujianjia
 * @since 2024/4/23
 */
public class AsyncDetectDemoPlugin implements DefenderAsyncDetectPlugin {

    private static final Map<String,Long> timeMap = new ConcurrentHashMap<>();
    @Override
    public DefenderDetectPluginResult submitDetectTask(DefenderDetectPluginRequest request) {
        timeMap.put(request.getNodeId(),System.currentTimeMillis());
        AlterShieldLoggerManager.log("info", Loggers.DEFENDER_PLUGIN, "AsyncDetectDemoPlugin",
                "submitDetectTask", request);
        return DefenderDetectPluginResult.success();
    }

    @Override
    public DefenderDetectPluginResult retrieveDetectResult(DefenderDetectPluginRequest request) {
        AlterShieldLoggerManager.log("info", Loggers.DEFENDER_PLUGIN, "AsyncDetectDemoPlugin",
                "retrieveDetectResult", "receive request ", request);
        long startTime = timeMap.get(request.getNodeId());
        DefenderDetectPluginResult rst = new DefenderDetectPluginResult();
        if (System.currentTimeMillis() - startTime < 1000 * 3) {
            AlterShieldLoggerManager.log("info", Loggers.DEFENDER_PLUGIN, "AsyncDetectDemoPlugin",
                    "retrieveDetectResult", "not ready ", request.getNodeId());
            rst.setDefenseFinished(false);
            return rst;
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
        rst.setDefensed(true);
        rst.setDetectGroupId(request.getDetectGroupId());
        rst.setStatus(status);
        rst.setMsg(message);
        Map<String,Object> result = new HashMap<>();
        result.put("status", status);
        rst.setResultJson(JSONObject.toJSONString(result));
        rst.setDefenseFinished(true);
        return rst;
    }
}
