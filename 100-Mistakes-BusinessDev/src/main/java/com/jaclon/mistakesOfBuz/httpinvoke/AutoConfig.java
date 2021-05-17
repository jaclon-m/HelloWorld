package com.jaclon.mistakesOfBuz.httpinvoke;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/5/17 17:48
 */
@Configuration
@EnableFeignClients(basePackages = "com.jaclon.mistakesOfBuz.httpinvoke")
public class AutoConfig {
}
