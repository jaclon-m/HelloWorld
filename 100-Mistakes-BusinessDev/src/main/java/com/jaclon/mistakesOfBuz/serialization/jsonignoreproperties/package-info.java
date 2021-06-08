/**
 * 1. 自定义ObjectMapper出现反序列化问题，无法忽略未知属性
 * Spring Boot 在自动配 置时贴心地做了全局设置。如果需要设置更多的特性，可以直接修改配置文件 spring.jackson.**
 * 或设置 Jackson2ObjectMapperBuilderCustomizer 回调接口，来 启用更多设置，无需重新定义 ObjectMapper Bean。
 *
 * Jackson 针对序列化和反序列化有大量的细节功能特性，我们可以参考 Jackson 官方文 档来了解这些特性，
 * 详见 SerializationFeature、 DeserializationFeature和 MapperFeature
 *
 *  Jackson2ObjectMapperBuilder 类源码的实现(注意 configure 方 法)，分析一下其除了关闭 FAIL_ON_UNKNOWN_PROPERTIES 外，还做了什么吗
 *
 * @author jaclon
 * @since 2021/6/8 12:01
 */
package com.jaclon.mistakesOfBuz.serialization.jsonignoreproperties;