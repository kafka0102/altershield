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
package com.alipay.altershield.defender.framework.request;

/**
 * Query defense service request structure
 *
 * @author yhaoxuan
 * @version QueryDefenderAbilityRequest.java, v 0.1 2022年11月22日 8:28 下午 yhaoxuan
 */
public class QueryDefenderAbilityRequest {

    /**
     * owner
     */
    private String owner;

    /**
     * service name
     */
    private String serviceName;

    /**
     * control service key
     */
    private String controlKey;

    /**
     * Getter method for property <tt>owner</tt>.
     *
     * @return property value of owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Setter method for property <tt>owner</tt>.
     *
     * @param owner value to be assigned to property owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Getter method for property <tt>serviceName</tt>.
     *
     * @return property value of serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Setter method for property <tt>serviceName</tt>.
     *
     * @param serviceName value to be assigned to property serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Getter method for property <tt>controlKey</tt>.
     *
     * @return property value of controlKey
     */
    public String getControlKey() {
        return controlKey;
    }

    /**
     * Setter method for property <tt>controlKey</tt>.
     *
     * @param controlKey value to be assigned to property controlKey
     */
    public void setControlKey(String controlKey) {
        this.controlKey = controlKey;
    }
}