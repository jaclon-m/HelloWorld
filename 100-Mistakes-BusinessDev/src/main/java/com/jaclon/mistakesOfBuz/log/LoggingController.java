package com.jaclon.mistakesOfBuz.log;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/6/1 12:33
 */
//@Log4j2
@Slf4j
@RequestMapping("/logging")
@RestController
public class LoggingController {

    @GetMapping("/log")
    public void log() {
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }
}