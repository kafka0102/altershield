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
/**
 * Alipay.com Inc. Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.alipay.altershield.pluginmarket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.altershield.common.constant.AlterShieldConstant;
import com.alipay.altershield.common.logger.Loggers;
import com.alipay.altershield.framework.common.util.logger.AlterShieldLoggerManager;
import com.alipay.altershield.framework.core.change.facade.result.AlterShieldResult;
import com.alipay.altershield.pluginmarket.manager.InnerPluginManager;
import com.alipay.altershield.shared.pluginmarket.PluginMarket;
import com.alipay.altershield.shared.pluginmarket.entity.MetaPluginEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * @author xiangyue
 * @version : PluginMarketImpl.java, v 0.1 2022-11-24 11:51 xiangyue Exp $$
 */
@Service
public class PluginMarketImpl implements PluginMarket, InitializingBean {

    @Autowired
    private ThreadPoolTaskScheduler pluginRefresher;

    @Autowired
    private InnerPluginManager innerPluginManager;

    /**
     * pluginKey -> mainClass
     */
    private final ConcurrentMap<String, MetaPluginEntity> allPluginEntities = new ConcurrentHashMap<>();

    /**
     * Constants
     */
    private static final String PLUGIN_KEY = "pluginKey";
    private static final String MAIN_CLASS = "mainClass";
    private static final String INVOKE_TYPE = "invokeType";

    @Override
    public boolean isEnablePlugin(String pluginKey) {
        return allPluginEntities.containsKey(pluginKey);
    }

    @Override
    public <PluginType, ResultType> AlterShieldResult<ResultType> executePlugin(String pluginKey, String mainClass, Function<PluginType, ResultType> invoker) {
        if (!allPluginEntities.containsKey(pluginKey)) {
            AlterShieldLoggerManager.log("info", Loggers.DEFENDER, "PluginMarketImpl", "executePlugin",
                    "error", "not found plugin ", pluginKey);
            throw new IllegalArgumentException("The corresponding plugin cannot be found" + pluginKey);
        }
        return innerPluginManager.executePlugin(pluginKey, mainClass, invoker);
        // todo outer plugin type supported by next version
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pluginRefresher.scheduleWithFixedDelay(() -> {
            JSONArray innerPlugins = JSON.parseArray(AlterShieldConstant.DEFENDER_INNER_PLUGINS);
            for (Object obj : innerPlugins) {
                JSONObject json = (JSONObject) obj;
                MetaPluginEntity plugin = new MetaPluginEntity();
                plugin.setMainClass(json.getString(MAIN_CLASS));
                plugin.setInvokeType(json.getString(INVOKE_TYPE));
                allPluginEntities.put(json.getString(PLUGIN_KEY), plugin);
            }
        }, 5000);
    }
}
