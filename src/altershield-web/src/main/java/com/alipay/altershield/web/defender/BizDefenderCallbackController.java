package com.alipay.altershield.web.defender;

import com.alipay.altershield.common.logger.Loggers;
import com.alipay.altershield.framework.common.util.logger.AlterShieldLoggerManager;
import com.alipay.altershield.framework.core.change.facade.callback.request.ChangeCheckCallbackWrapperRequest;
import com.alipay.altershield.framework.core.change.facade.callback.result.ChangeCheckCallbackResult;
import com.alipay.altershield.framework.core.change.facade.result.AlterShieldResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yujianjia
 * @since 2024/4/23
 */
@Api
@CrossOrigin
@Controller
@RequestMapping("/biz/defender")
public class BizDefenderCallbackController {

    private final Map<String, ChangeCheckCallbackWrapperRequest> callbackRequestMap = new ConcurrentHashMap<>();

    @ApiOperation("回调示例")
    @ResponseBody
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public AlterShieldResult<ChangeCheckCallbackResult> callback(@RequestBody @Valid ChangeCheckCallbackWrapperRequest param) {
        AlterShieldLoggerManager.log("info", Loggers.BIZ_SERVICE, "BizDefenderCallbackController", "callback", param);
        callbackRequestMap.put(param.getCallbackRequest().getNodeId(), param);
        ChangeCheckCallbackResult result = new ChangeCheckCallbackResult(true, false, "");
        return AlterShieldResult.succeed("success", result);
    }

    @ApiOperation("查询回调参数")
    @ResponseBody
    @RequestMapping(value = "/getCallbackRequest", method = RequestMethod.GET)
    public AlterShieldResult<ChangeCheckCallbackWrapperRequest> getCallbackRequest(@RequestParam String nodeId) {
        AlterShieldLoggerManager.log("info", Loggers.BIZ_SERVICE, "BizDefenderCallbackController", "getCallbackRequest", nodeId);
        return AlterShieldResult.succeed("success", callbackRequestMap.get(nodeId));
    }
}
