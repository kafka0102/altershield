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
package com.alipay.altershield.spi.defender.model.request;

import javax.validation.constraints.NotNull;

/**
 * The basic information structure of the change
 *
 * @author yhaoxuan
 * @version ChangeBaseInfo.java, v 0.1 2022年08月08日 4:26 下午 yhaoxuan
 */
public class ChangeBaseInfo {

    /**
     * Change initiation platform
     */
    @NotNull
    private String platform;

    /**
     * Title of the change
     */
    private String changeTitle;

    /**
     * Creator of the change
     */
    @NotNull
    private String creator;

    /**
     * Executor of the change
     */
    @NotNull
    private String executor;

    /**
     * Change content information
     */
    @NotNull
    private ChangeContent changeContent;

    /**
     * Change request parameters
     */
    private String changeParamJSON;

    /**
     * Getter method for property <tt>platform</tt>.
     *
     * @return property value of platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Setter method for property <tt>platform</tt>.
     *
     * @param platform value to be assigned to property platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * Getter method for property <tt>changeTitle</tt>.
     *
     * @return property value of changeTitle
     */
    public String getChangeTitle() {
        return changeTitle;
    }

    /**
     * Setter method for property <tt>changeTitle</tt>.
     *
     * @param changeTitle value to be assigned to property changeTitle
     */
    public void setChangeTitle(String changeTitle) {
        this.changeTitle = changeTitle;
    }

    /**
     * Getter method for property <tt>creator</tt>.
     *
     * @return property value of creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Setter method for property <tt>creator</tt>.
     *
     * @param creator value to be assigned to property creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Getter method for property <tt>executor</tt>.
     *
     * @return property value of executor
     */
    public String getExecutor() {
        return executor;
    }

    /**
     * Setter method for property <tt>executor</tt>.
     *
     * @param executor value to be assigned to property executor
     */
    public void setExecutor(String executor) {
        this.executor = executor;
    }

    /**
     * Getter method for property <tt>changeContent</tt>.
     *
     * @return property value of changeContent
     */
    public ChangeContent getChangeContent() {
        return changeContent;
    }

    /**
     * Setter method for property <tt>changeContent</tt>.
     *
     * @param changeContent value to be assigned to property changeContent
     */
    public void setChangeContent(ChangeContent changeContent) {
        this.changeContent = changeContent;
    }

    /**
     * Getter method for property <tt>changeParamJSON</tt>.
     *
     * @return property value of changeParamJSON
     */
    public String getChangeParamJSON() {
        return changeParamJSON;
    }

    /**
     * Setter method for property <tt>changeParamJSON</tt>.
     *
     * @param changeParamJSON value to be assigned to property changeParamJSON
     */
    public void setChangeParamJSON(String changeParamJSON) {
        this.changeParamJSON = changeParamJSON;
    }
}