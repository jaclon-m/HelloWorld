/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jason.java.io;

import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @Classname EmployeeProp
 * @Description 属性读取
 *
 * @author jaclon
 * @date 2019/11/14
 */
public class EmployeeProp {

    public static void main(String[] args) {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            // 要加载的属性文件
            in = EmployeeProp.class.getClassLoader().getResourceAsStream("employee.properties");
            properties.load(in);
            System.out.println("load properties success");
            String scrz = properties.getProperty("scrz");
            List<String> list = Arrays.asList(StringUtils.split(scrz, ","));
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            /*Enumeration<String> names = (Enumeration<String>) properties.propertyNames();
            while (names.hasMoreElements()){
                System.out.println(names.nextElement());
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
