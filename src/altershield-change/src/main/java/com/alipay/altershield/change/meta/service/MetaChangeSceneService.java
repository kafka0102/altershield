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
 * Copyright Notice: This software is developed by Ant Small and Micro Financial Services Group Co., Ltd. This software and
 *  all the relevant information, including but not limited to any signs, images, photographs, animations, text,
 *  interface design, audios and videos, and printed materials, are protected by copyright laws and other intellectual
 *  property laws and treaties.
 *
 * The use of this software shall abide by the laws and regulations as well as Software Installation License
 * Agreement/Software Use Agreement updated from time to time. Without authorization from Ant Small and Micro Financial
 *  Services Group Co., Ltd., no one may conduct the following actions:
 *
 *   1) reproduce, spread, present, set up a mirror of, upload, download this software;
 *
 *   2) reverse engineer, decompile the source code of this software or try to find the source code in any other ways;
 *
 *   3) modify, translate and adapt this software, or develop derivative products, works, and services based on this
 *    software;
 *
 *   4) distribute, lease, rent, sub-license, demise or transfer any rights in relation to this software, or authorize
 *    the reproduction of this software on other’s computers.
 */
package com.alipay.altershield.change.meta.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.altershield.change.meta.model.MetaBaseChangeSceneEntity;
import com.alipay.altershield.change.meta.model.MetaChangeSceneEntity;
import com.alipay.altershield.change.meta.model.effective.MetaChangeStepEntity;
import com.alipay.altershield.change.meta.service.request.*;
import com.alipay.altershield.framework.core.change.facade.request.CreateMetaChangeSceneRequest;
import com.alipay.altershield.framework.core.change.facade.result.AlterShieldPageResult;
import com.alipay.altershield.framework.core.change.facade.result.AlterShieldResult;
import com.alipay.altershield.framework.core.change.facade.result.CreateMetaChangeSceneResult;
import com.alipay.altershield.shared.change.exe.entity.MetaChangeSceneBatchEntity;

import java.util.List;

/**
 * @author yuanji
 * @version : MetaChangeSceneService.java, v 0.1 2022年03月15日 11:50 上午 yuanji Exp $
 */
public interface MetaChangeSceneService {

    /**
     * 根据id获取变更场景的完整对象
     *
     * @param id
     * @return
     */
    AlterShieldResult<MetaChangeSceneEntity> getMetaChangeSceneById(String id);

    /**
     * 根据changeKey获取变更场景的完整对象
     *
     * @param changeSceneKey
     * @return
     */
    MetaChangeSceneEntity getMetaChangeSceneByChangeSceneKey(String changeSceneKey);

    /**
     * 根据changeKey来获取变更步骤实体
     *
     * @param changeKey
     * @return
     */
    MetaChangeStepEntity getMetaChangeStepByChangeKey(String changeKey);

    /**
     * 获取order级别changeStep
     *
     * @param changeSceneKey
     * @return
     */
    MetaChangeStepEntity getOrderMetaChangeStep(String changeSceneKey);

    /**
     * 获取批次级别changeStep
     *
     * @param changeSceneKey
     * @return
     */
    MetaChangeStepEntity getBatchMetaChangeStep(String changeSceneKey);

    /**
     * 创建或者更新场景信息
     *
     * @param id                           场景id
     * @param createMetaChangeSceneRequest
     * @return 返回场景id
     */
    AlterShieldResult<CreateMetaChangeSceneResult> createOrUpdateChangeScene(String id, CreateMetaChangeSceneRequest createMetaChangeSceneRequest);

    /**
     * 创建变更场景信息
     *
     * @param createMetaChangeSceneRequest
     * @return 返回场景id
     */
    AlterShieldResult<CreateMetaChangeSceneResult> createTempChangeScene(CreateMetaChangeSceneRequest createMetaChangeSceneRequest);


    /**
     * 创建release状态的变更场景
     *
     * @param createMetaChangeSceneRequest
     * @return
     */
    AlterShieldResult<CreateMetaChangeSceneResult> createReleaseChangeScene(CreateMetaChangeSceneRequest createMetaChangeSceneRequest);

    /**
     * 根据变更场景的基本信息
     *
     * @param updateMetaChangeSceneRequest
     * @return
     */
    AlterShieldResult<Boolean> updateBasicChangeScene(UpdateMetaChangeSceneRequest updateMetaChangeSceneRequest);

    /**
     * 更新变更步骤
     *
     * @param request
     * @return
     */
    AlterShieldResult<Boolean> updateChangeStep(UpdateMetaChangeStepRequest request);

    /**
     * 创建变更步骤
     *
     * @param request
     * @return
     */
    AlterShieldResult<Boolean> createChangeStep(CreateMetaChangeStepRequest request);

    /**
     * 删除变更步骤
     *
     * @param id
     * @return
     */
    AlterShieldResult<Boolean> removeChangeStep(String id);


    /**
     * 根据id 删除场景
     *
     * @param id
     * @return
     */
    AlterShieldResult<Boolean> removeChangeScene(String id);


    /**
     * 变更场景查询
     *
     * @param request
     * @return
     */
    AlterShieldPageResult<List<MetaBaseChangeSceneEntity>> query(QueryChangeSceneRequest request);

    /**
     * 第二步创建变更场景
     *
     * @param request
     * @return
     */
    AlterShieldResult<Boolean> createChangeScene(CreateMetaChangeScene2Request request);

    /**
     * 导入变更场景配置
     *
     * @param config
     * @return
     */
    AlterShieldResult<String> importChangeScene(JSONObject config);

    /**
     * 导出变更场景配置
     *
     * @param id
     * @return
     */
    AlterShieldResult<String> exportChangeScene(String id);

    /**
     * 检查changeKey是否存在
     *
     * @param changeKey
     * @return
     */
    AlterShieldResult<Boolean> checkChangeKey(String changeKey);


    /**
     * 检查changeKey是否存在
     *
     * @param changeKey
     * @return
     */
    AlterShieldResult<Boolean> onlyCheckChangeKey(String changeKey);

    /**
     * 变更场景代G
     *
     * @param request
     * @return
     */
    AlterShieldResult<String> alterGeneration(AlterChangeSceneGenerationRequest request);


    /**
     * 用于同步serviceKey的配置
     *
     * @param request the request
     * @return the ops cloud result
     */
    AlterShieldResult<Boolean> syncServiceKey(SyncMetaChangeSceneRequest request);
}