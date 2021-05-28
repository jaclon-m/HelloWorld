package com.jaclon.mistakesOfBuz;

import com.jaclon.mistakesOfBuz.common.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

/**
 * 业务开发常见错误100例
 * @author jaclon
 * @since 2021/1/22 下午12:42
 */
@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        // httpinvoke
//        Utils.loadPropertySource(Application.class, "default.properties");
        SpringApplication.run(Application.class, args);
    }


}
