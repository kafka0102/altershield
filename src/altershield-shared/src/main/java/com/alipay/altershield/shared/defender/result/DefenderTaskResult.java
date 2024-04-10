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
package com.alipay.altershield.shared.defender.result;

/**
 * Defender detect task scheduling results
 *
 * @author yhaoxuan
 * @version DefenderTaskExecuteResult.java, v 0.1 2022年08月30日 3:30 下午 yhaoxuan
 */
public class DefenderTaskResult {

    /**
     * Is task scheduling successful
     */
    private boolean success;

    /**
     * When task failed, need to be retried or not
     */
    private boolean needRetry;

    /**
     * Number of retries
     */
    private long retryCount;

    /**
     * Task scheduling information
     */
    private String msg;

    /**
     * Fail without retrying
     *
     * @param msg fail message
     * @return Task execution result structure
     */
    public static DefenderTaskResult failWithNoRetry(String msg) {
        DefenderTaskResult result = new DefenderTaskResult();
        result.setSuccess(false);
        result.setNeedRetry(false);
        result.setMsg(msg);

        return result;
    }

    /**
     * Failed and needs to retry
     *
     * @param msg fail message
     * @return Task execution result structure
     */
    public static DefenderTaskResult failWithRetry(String msg) {
        DefenderTaskResult result = new DefenderTaskResult();
        result.setSuccess(false);
        result.setNeedRetry(true);
        result.setMsg(msg);

        return result;
    }

    /**
     * Task succeed
     *
     * @param msg execution message
     * @return Task execution result structure
     */
    public static DefenderTaskResult succeed(String msg) {
        DefenderTaskResult result = new DefenderTaskResult();
        result.setSuccess(true);
        result.setNeedRetry(false);
        result.setMsg(msg);

        return result;
    }

    /**
     * Getter method for property <tt>retryCount</tt>.
     *
     * @return property value of retryCount
     */
    public long getRetryCount() {
        return retryCount;
    }

    /**
     * Setter method for property <tt>retryCount</tt>.
     *
     * @param retryCount value to be assigned to property retryCount
     */
    public void setRetryCount(long retryCount) {
        this.retryCount = retryCount;
    }

    /**
     * Getter method for property <tt>success</tt>.
     *
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>needRetry</tt>.
     *
     * @return property value of needRetry
     */
    public boolean isNeedRetry() {
        return needRetry;
    }

    /**
     * Setter method for property <tt>needRetry</tt>.
     *
     * @param needRetry value to be assigned to property needRetry
     */
    public void setNeedRetry(boolean needRetry) {
        this.needRetry = needRetry;
    }

    /**
     * Getter method for property <tt>msg</tt>.
     *
     * @return property value of msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Setter method for property <tt>msg</tt>.
     *
     * @param msg value to be assigned to property msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}