/**
 *
 * 反序列化时要小心类的构造方法
 * 默认情况下，在反序列化的时候，Jackson 框架只会调用无参构 造方法创建对象。
 * 如果走自定义的构造方法创建对象，需要通过 @JsonCreator 来指定构 造方法，并通过 @JsonProperty 设置构造方法中参数对应的 JSON 属性名
 *
 * @author jaclon
 * @since 2021/6/8 12:12
 */
package com.jaclon.mistakesOfBuz.serialization.deseriallization;