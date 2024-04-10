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
package com.alipay.altershield.shared.defender;

import com.alipay.altershield.framework.core.change.facade.result.AlterShieldResult;
import com.alipay.altershield.shared.defender.request.DefenderDetectRequest;
import com.alipay.altershield.shared.defender.result.DefenderDetectResult;
import com.alipay.altershield.shared.defender.result.DefenderDetectSubmitResult;

/**
 * The main process service that initiates defender detection
 *
 * @author yhaoxuan
 * @version OpsCloudDefenderService.java, v 0.1 2022年08月08日 4:10 下午 yhaoxuan
 */
public interface DefenderDetectService {

    /**
     * Synchronous execution of change defense detection entry - only available for pre defense stage in G1 generation
     * Note: The defense rules for implementing asynchronous defense SPI are not executed in synchronous defense.
     *
     * @param request Change defense detection request structure
     * @return Change defense detection result structure
     */
    AlterShieldResult<DefenderDetectResult> syncDetect(DefenderDetectRequest request);

    /**
     * Asynchronous execution of change defense detection entry - used by G1 post, G2, G3, G4 generation
     *
     * @param request Change defense verification request structure
     * @return Change defense detection task submission results
     */
    AlterShieldResult<DefenderDetectSubmitResult> asyncDetect(DefenderDetectRequest request);
}