/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.bigdata.druid;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname DruidTest
 * @Description TODO
 *
 * @author jaclon
 * @date 2019/11/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DruidTest {

    @Test
    public void test1(){

        Fields fieldsA = new Fields();
        Fields fieldsB = new Fields();

        List<Object> modelA30 = new ArrayList<>();

        Field fieldObjectA30 = new Field();
        fieldObjectA30.setType("or");
        fieldObjectA30.setDimension("AA");
        fieldObjectA30.setValue("11");

        modelA30.add(fieldObjectA30);

        //////////////////////////////////////////////////

        List<Object> modelA31 = new ArrayList<>();

        Field fieldObjectA31 = new Field();
        fieldObjectA31.setType("or");
        fieldObjectA31.setDimension("BB");
        fieldObjectA31.setValue("22");

        Field fieldObjectA32 = new Field();
        fieldObjectA32.setType("or");
        fieldObjectA32.setDimension("CC");
        fieldObjectA32.setValue("33");


        modelA31.add(fieldObjectA31);
        modelA31.add(fieldObjectA32);


        fieldsB.setType("and");
        fieldsB.setFields(modelA31);

        modelA30.add(fieldsB);

        ///////////////////////////////////////////////////////

        fieldsA.setType("and");
        fieldsA.setFields(modelA30);


        String result = JSONObject.toJSONString(fieldsA);
        System.out.println(result);


    }
}
