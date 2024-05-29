package com.alipay.altershield.framework.core.change.ark;

import java.util.List;

/**
 * @author yujianjia
 * @since 2024/5/13
 */
public class ChangeCheckVerdict {
    // PASS, FAIL, NONE
    String verdict;
    List<String> failedReasons;
    List<String> failedCodes;
}
