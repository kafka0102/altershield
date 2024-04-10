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
package com.alipay.altershield.shared.defender.enums;

/**
 * Change defense rule calling method enumeration
 *
 * @author haoxuan
 * @version DefenderPluginInvokeTypeEnum.java, v 0.1 2022年11月15日 9:06 下午 haoxuan
 */
public enum DefenderPluginInvokeTypeEnum {

    /**
     * Synchronize
     */
    SYNC("SYNC"),

    /**
     * Asynchronous polling
     */
    ASYNC_POLLING("ASYNC_POLLING"),

    /**
     * Asynchronous callback
     */
    ASYNC_CALLBACK("ASYNC_CALLBACK"),
    ;

    private final String type;

    DefenderPluginInvokeTypeEnum(String type) {
        this.type = type;
    }

    /**
     * Get the corresponding enumeration value based on the string
     *
     * @param type String value corresponding to the status
     * @return Corresponding enumeration value
     */
    public static DefenderPluginInvokeTypeEnum getByType(String type) {
        for (DefenderPluginInvokeTypeEnum e : DefenderPluginInvokeTypeEnum.values()) {
            if (e.getType().equalsIgnoreCase(type)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }
}