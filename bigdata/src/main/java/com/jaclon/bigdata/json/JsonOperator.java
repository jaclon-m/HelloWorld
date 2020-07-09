/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.bigdata.json;


import com.alibaba.fastjson.JSONObject;

/**
 * Json 操作器
 *
 * @author SilkwormSay
 * @date 2019/4/15
 */
public final class JsonOperator {

  /**
   * 私有构造
   */
  private JsonOperator() {}

  /**
   * Json 串转换 Java 对象
   *
   * @param jsonStr
   * @return
   */
  public static <T> T convertJavaObj(String jsonStr, T clazz) {
    return (T) JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), clazz.getClass());
  }
}
