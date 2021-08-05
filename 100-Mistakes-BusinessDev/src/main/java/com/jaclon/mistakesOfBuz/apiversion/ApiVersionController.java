package com.jaclon.mistakesOfBuz.apiversion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 4中版本实现方式
 *
 * @author jaclon
 * @since 2021/8/5 20:25
 */
@RestController
public class ApiVersionController {
    @GetMapping("/api/item/v1")
    public void wrong1() {

    }

    @GetMapping("/api/v1/shop")
    public void wrong2() {

    }

    @GetMapping("/v1/api/merchant")
    public void wrong3() {

    }

    @GetMapping("/v1/api/user")
    public int right1() {
        return 1;
    }

    @GetMapping(value = "/api/user", params = "version=2")
    public int right2(@RequestParam("version") int version) {
        return 2;
    }

    @GetMapping(value = "/api/user", headers = "X-API-VERSION=3")
    public int right3(@RequestHeader("X-API-VERSION") int version) {
        return 3;
    }

//    @GetMapping(value = "/api/user")
//    @APIVersion("v4")
//    public int right4() {
//        return 4;
//    }
}
