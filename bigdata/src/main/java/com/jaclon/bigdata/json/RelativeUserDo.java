/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.bigdata.json;

import lombok.Data;

/**
 * @Classname RelativeUserDo
 * @Description 关联用户列表详情
 *
 * @author jaclon
 * @date 2019/11/29
 */
@Data
public class RelativeUserDo{

    /**
     * 身份证号
     */
    private String certCardNo;

    /**
     * 客户ID
     */
    private String customerId;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 渠道
     */
    private String channelId;
    /**
     * 联系人1电话
     */
    private String phone1;
    /**
     * 联系人2电话
     */
    private String phone2;
    /**
     * 收货人手机号
     */
    private String receiptPhone;
    /**
     * 单位电话(表中没有）
     */
    private String companyPhone;
    /**
     * (注册）推荐码
     */
    private String registCode;
    /**
     * 户籍地址
     */
    private String idAddress;
    /**
     * 收货地址
     */
    private String receiptAddress;

    /**
     * 居住地址
     */
    private String homeAddress;

    /**
     * 收货地址GPS
     */
    private String consigneeGps;

    /**
     * 下单地址GPS
     */
    private String gpsLocation;

    /**
     * 设备号
     */
    private String deviceCode;


}
