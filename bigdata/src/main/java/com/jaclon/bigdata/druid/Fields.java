/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.bigdata.druid;

import lombok.Data;

import java.util.List;

/**
 * @Classname Fields
 * @Description TODO
 *
 * @author jaclon
 * @date 2019/11/27
 */
@Data
public class Fields {
    private String type;
    private List<Object> fields;
}
