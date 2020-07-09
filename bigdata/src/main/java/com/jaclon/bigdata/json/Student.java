/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.bigdata.json;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Classname Student
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/1/13
 */
@Data
public class Student implements Serializable {


    @JSONField(name = "name_stu")
    private String nameStu;
    private String address;
    private BigDecimal age;
    private DateTime time;

    private Integer number;
}
