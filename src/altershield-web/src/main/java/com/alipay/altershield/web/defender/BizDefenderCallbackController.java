package com.alipay.altershield.web.defender;

import com.alipay.altershield.common.logger.Loggers;
import com.alipay.altershield.framework.common.util.logger.AlterShieldLoggerManager;
import com.alipay.altershield.framework.core.change.facade.result.AlterShieldResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yujianjia
 * @since 2024/4/23
 */
@Api
@CrossOrigin
@Controller
@RequestMapping("/biz/defender")
public class BizDefenderCallbackController {

    @ApiOperation("回调示例")
    @ResponseBody
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public AlterShieldResult<String> callback(@RequestBody @Valid Object param) {
        AlterShieldLoggerManager.log("info", Loggers.BIZ_SERVICE, "BizDefenderCallbackController", "callback",
                "param", param);
        return AlterShieldResult.succeed("success", null);
    }
}
