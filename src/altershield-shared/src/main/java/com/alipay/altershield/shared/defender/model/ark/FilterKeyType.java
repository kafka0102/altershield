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
package com.alipay.altershield.shared.defender.model.ark;

/**
 * Enumeration of lvalue types supported when matching defense rules
 *
 * @author yuefan.wyf
 * @version $Id: FilterKeyEnum.java, v 0.1 2019年12月18日 下午10:18 yuefan.wyf Exp $
 */
public enum FilterKeyType {
    /**
     * 变更参数
     */
    CHANGE_PARAM("CHANGE_PARAM"),

    /**
     * 变更结果参数
     */
    CHANGE_RESULT("CHANGE_RESULT"),

    /**
     * 业务资源ID
     */
    BIZ_RESOURCE_ID("BIZ_RESOURCE_ID"),
    /**
     * 变更标题
     */
    CHANGE_TITLE("CHANGE_TITLE"),
    /**
     * 变更环境
     */
    CHANGE_ENV("CHANGE_ENV"),

    /**
     * 变更批次名称
     */
    CHANGE_PHASE_BATCH_NAME("CHANGE_PHASE_BATCH_NAME"),

    /**
     * 变更批次号
     */
    CHANGE_PHASE_BATCH_NO("CHANGE_PHASE_BATCH_NO"),
    ;

    /**
     * Type of the filter key
     */
    private final String type;

    FilterKeyType(String type) {
        this.type = type;
    }

    /**
     * Gets get type.
     *
     * @return the get type
     */
    public String getType() {
        return type;
    }

    /**
     * get enum value by type.
     *
     * @param type the string value
     * @return the enumeration object
     */
    public static FilterKeyType getByCode(String type) {
        for (FilterKeyType e : FilterKeyType.values()) {
            if (e.getType().equalsIgnoreCase(type)) {
                return e;
            }
        }

        return null;
    }
}