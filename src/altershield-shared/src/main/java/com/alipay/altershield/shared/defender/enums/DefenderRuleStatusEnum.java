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
 * Defense rule status enumeration
 *
 * @author yhaoxuan
 * @version DefenderRuleStatusEnum.java, v 0.1 2022年08月25日 9:37 下午 yhaoxuan
 */
public enum DefenderRuleStatusEnum {

    /**
     * disabled
     */
    DISABLED("DISABLED"),

    /**
     * Trial run
     */
    GRAY("GRAY"),

    /**
     * enabled
     */
    ENABLED("ENABLED"),
    ;

    /**
     * status
     */
    private final String status;

    DefenderRuleStatusEnum(String status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the corresponding enumeration value based on the string
     *
     * @param status String value corresponding to the status
     * @return Corresponding enumeration value
     */
    public static DefenderRuleStatusEnum getByStatus(String status) {
        for (DefenderRuleStatusEnum e : DefenderRuleStatusEnum.values()) {
            if (e.getStatus().equalsIgnoreCase(status)) {
                return e;
            }
        }
        return null;
    }
}