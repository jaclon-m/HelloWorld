/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.validator.entity;

import com.jaclon.validator.validator.MyAnnotation;

import java.io.Serializable;

/**
 * @Classname BaseValidateObj
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/19
 */
public class BaseValidateObj implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 记录唯一标识
     */
    @MyAnnotation(value = "ssss",field = "fffff")
    private String reqId;
}
