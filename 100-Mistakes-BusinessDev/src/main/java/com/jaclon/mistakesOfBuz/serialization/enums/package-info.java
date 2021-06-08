/**
 * 客户端和服务端的枚举定义不一致时，会出异常
 * 枚举序列化反序列化实现自定义的字段非常麻烦，会涉及 Jackson 的 Bug - 关于使用 code 还是desc
 * 总结 ：对于枚举，我建议尽量在程序内 部使用，而不是作为 API 接口的参数或返回值
 *
 * @author jaclon
 * @since 2021/6/8 12:14
 */
package com.jaclon.mistakesOfBuz.serialization.enums;