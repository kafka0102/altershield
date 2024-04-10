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

import org.jetbrains.annotations.NotNull;
import java.util.List;

/**
 * Structure definition of change content
 *
 * @author yhaoxuan
 * @version ChangeContent.java, v 0.1 2022年08月08日 4:37 下午 yhaoxuan
 */
public class ChangeContent {

    /**
     * Change content type (configuration item/application system/container...)
     */
    @NotNull
    private String changeContentType;

    /**
     * Change content instance (take configuration change as an example: the name of the configuration item)
     */
    @NotNull
    private List<String> changeContentInstance;

    /**
     * Change content value (take configuration change as an example: the value of the configuration item to push)
     */
    private String changeContentValue;

    /**
     * Getter method for property <tt>changeContentType</tt>.
     *
     * @return property value of changeContentType
     */
    public String getChangeContentType() {
        return changeContentType;
    }

    /**
     * Setter method for property <tt>changeContentType</tt>.
     *
     * @param changeContentType value to be assigned to property changeContentType
     */
    public void setChangeContentType(String changeContentType) {
        this.changeContentType = changeContentType;
    }

    /**
     * Getter method for property <tt>changeContentInstance</tt>.
     *
     * @return property value of changeContentInstance
     */
    public List<String> getChangeContentInstance() {
        return changeContentInstance;
    }

    /**
     * Setter method for property <tt>changeContentInstance</tt>.
     *
     * @param changeContentInstance value to be assigned to property changeContentInstance
     */
    public void setChangeContentInstance(List<String> changeContentInstance) {
        this.changeContentInstance = changeContentInstance;
    }

    /**
     * Getter method for property <tt>changeContentValue</tt>.
     *
     * @return property value of changeContentValue
     */
    public String getChangeContentValue() {
        return changeContentValue;
    }

    /**
     * Setter method for property <tt>changeContentValue</tt>.
     *
     * @param changeContentValue value to be assigned to property changeContentValue
     */
    public void setChangeContentValue(String changeContentValue) {
        this.changeContentValue = changeContentValue;
    }
}