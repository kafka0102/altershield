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
 * Enumeration of expression types supported when matching
 *
 * @author yuefan.wyf
 * @version $Id: MatchTypeEnum.java, v 0.1 2019年12月05日 下午12:08 yuefan.wyf Exp $
 */
public enum MatchType {
    EQUAL("EQUAL"),
    NOT_EQUAL("NOT_EQUAL"),
    CONTAINS("CONTAINS"),
    CONTAINS_NONE("CONTAINS_NONE"),
    START_WITH("START_WITH"),
    NOT_START_WITH("NOT_START_WITH"),
    END_WITH("END_WITH"),
    NOT_END_WITH("NOT_END_WITH"),
    LESS_THAN("LESS_THAN"),
    GREATER_THAN("GREATER_THAN"),
    BELONG_TO("BELONG_TO"),
    ;

    /**
     * match type
     */
    private final String type;

    private MatchType(String type) {
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
    public static MatchType getByCode(String type) {
        for (MatchType e : MatchType.values()) {
            if (e.getType().equalsIgnoreCase(type)) {
                return e;
            }
        }

        return null;
    }
}