package com.jaclon.mistakesOfBuz.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/5/17 18:03
 */
@Slf4j
public class Utils {
    public static void loadPropertySource(Class clazz,String fileName){
        try {
            Properties p=new Properties();
            p.load(clazz.getClassLoader().getResourceAsStream(fileName));
            p.forEach((k,v)->{
                log.info("{}={}",k,v);
                System.setProperty(k.toString(),v.toString());
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
