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
package com.alipay.altershield.shared.pluginmarket;


import com.alipay.altershield.framework.core.change.facade.result.AlterShieldResult;

import java.util.function.Function;

/**
 * the main process service of plugin market
 *
 * @author xiangyue
 * @version : PluginMarket.java, v 0.1 2022-11-24 11:26 xiangyue Exp $$
 */
public interface PluginMarket {

    /**
     * Is an enabled plugin
     *
     * @param pluginKey the plugin key
     * @return is enabled or not
     */
    boolean isEnablePlugin(String pluginKey);

    /**
     * Execute plugin
     *
     * @param pluginKey the plugin key
     * @param mainClass the main class
     * @param invoker invoke function
     * @param <PluginType> type of the plugin
     * @param <ResultType> result type of the plugin
     * @return 插件执行结果
     */
    <PluginType, ResultType> AlterShieldResult<ResultType> executePlugin(String pluginKey, String mainClass,
                                                                         Function<PluginType, ResultType> invoker);
}
