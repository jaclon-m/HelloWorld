/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.message.kafka;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Message
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/2/12
 */
@Data
public class Message implements Serializable {
    /** serialVersionUID*/
    private static final long serialVersionUID = -904891181560314395L;
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳
}
