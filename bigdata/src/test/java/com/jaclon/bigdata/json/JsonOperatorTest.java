/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.bigdata.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname JsonOperatorTest
 * @Description TODO
 *
 * @author jaclon
 * @date 2019/12/2
 */
public class JsonOperatorTest {

    public static void main(String[] args) throws IOException {
        /*String str = "[{\"certCardNo\":\"510922199509266011\",\"customerId\":\"66688947\",\"customerName\":\"王勇攀\",\"mobile\":\"15555229650\",\"channelId\":\"9\",\"phone1\":\"\\\"13258483990\\\"\",\"phone2\":\"\\\"13258488793\\\"\",\"receiptPhone\":\"\",\"companyPhone\":\"087167951811\",\"registCode\":\"flloon\",\"receiptAddress\":\"\",\"homeAddress\":\"\",\"consigneeGps\":\"null,null\",\"gpsLocation\":\"\",\"deviceCode\":\"ffffffff-ec12-4950-ffff-ffffea8e542c\"},{\"certCardNo\":\"371081199303114827\",\"customerId\":\"66689575\",\"customerName\":\"刘玲玉\",\"mobile\":\"18254598098\",\"channelId\":\"9\",\"phone1\":\"\\\"18053500726\\\"\",\"phone2\":\"\\\"15192287686\\\"\",\"receiptPhone\":\"\",\"companyPhone\":\"05356661686\",\"registCode\":\"\",\"receiptAddress\":\"\",\"homeAddress\":\"\",\"consigneeGps\":\"null,null\",\"gpsLocation\":\"\",\"deviceCode\":\"ffffffff-ec12-4950-ffff-ffffea8e542c\"},{\"certCardNo\":\"610502199304052411\",\"customerId\":\"66690199\",\"customerName\":\"李海海\",\"mobile\":\"13279361993\",\"channelId\":\"6\",\"phone1\":\"\\\"13835411910\\\"\",\"phone2\":\"\\\"18502901613\\\"\",\"receiptPhone\":\"\",\"companyPhone\":\"02968571885\",\"registCode\":\"y3579\",\"receiptAddress\":\"\",\"homeAddress\":\"\",\"consigneeGps\":\"null,null\",\"gpsLocation\":\"\",\"deviceCode\":\"ffffffff-ec12-4950-ffff-ffffea8e542c\"},{\"certCardNo\":\"610502199304052411\",\"customerId\":\"66690199\",\"customerName\":\"李海海\",\"mobile\":\"13279361993\",\"channelId\":\"6\",\"phone1\":\"\\\"13835411910\\\"\",\"phone2\":\"\\\"18502901613\\\"\",\"receiptPhone\":\"\",\"companyPhone\":\"02968571885\",\"registCode\":\"y3579\",\"receiptAddress\":\"\",\"homeAddress\":\"\",\"consigneeGps\":\"null,null\",\"gpsLocation\":\"\",\"deviceCode\":\"ffffffff-ec12-4950-ffff-ffffea8e542c\"},{\"certCardNo\":\"422801199601071236\",\"customerId\":\"66690426\",\"customerName\":\"向果夫\",\"mobile\":\"13997579724\",\"channelId\":\"9\",\"phone1\":\"\\\"13997763287\\\"\",\"phone2\":\"\\\"13997750520\\\"\",\"receiptPhone\":\"\",\"companyPhone\":\"07188020688\",\"registCode\":\"flloon\",\"receiptAddress\":\"\",\"homeAddress\":\"\",\"consigneeGps\":\"null,null\",\"gpsLocation\":\"\",\"deviceCode\":\"ffffffff-ec12-4950-ffff-ffffea8e542c\"},{\"certCardNo\":\"431129199209135222\",\"customerId\":\"66690202\",\"customerName\":\"莫云燕\",\"mobile\":\"13725316261\",\"channelId\":\"6\",\"phone1\":\"\\\"18814090673\\\"\",\"phone2\":\"\\\"18143311746\\\"\",\"receiptPhone\":\"\",\"companyPhone\":\"17374619062\",\"registCode\":\"y3579\",\"receiptAddress\":\"\",\"homeAddress\":\"\",\"consigneeGps\":\"null,null\",\"gpsLocation\":\"\",\"deviceCode\":\"ffffffff-ec12-4950-ffff-ffffea8e542c\"},{\"certCardNo\":\"522633199303010017\",\"customerId\":\"66591129\",\"customerName\":\"明继鹏\",\"mobile\":\"13208838801\",\"channelId\":\"9\",\"phone1\":\"\\\"17716605750\\\"\",\"phone2\":\"\\\"15185552273\\\"\",\"receiptPhone\":\"\",\"companyPhone\":\"08556441066\",\"registCode\":\"flloon\",\"receiptAddress\":\"\",\"homeAddress\":\"\",\"consigneeGps\":\"null,null\",\"gpsLocation\":\"\",\"deviceCode\":\"ffffffff-ec12-4950-ffff-ffffea8e542c\"}]";
        //Class<RelativeUserDo> relativeUserDoClass = JsonOperator.convertJavaObj(str, RelativeUserDo.class);
               ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, RelativeUserDo.class);
        List<RelativeUserDo>list = mapper.readValue(str,javaType);
        System.out.println(list.get(2).toString());*/

        test4();
    }

    /*public static  void test1() throws IOException {
        //定义两种不同格式的字符串
        String objectStr="{\"name_stu\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\",\"time\":\"2020-01-10 00:00:00\"}";
        String arrayStr="[{\"name_stu\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}]";

        *//*ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Student.class);
        List<Student> stu = objectMapper.readValue(arrayStr, javaType);*//*
        *//*List<Student> stu = JSON.parseArray(arrayStr, Student.class);
        System.out.println(stu.get(0));*//*
        LocalDateTime localDateTime = new LocalDateTime();
        localDateTime.toString();
        Student student = JSON.parseObject(objectStr, Student.class);
        System.out.println(student.getTime().toLocalDateTime().toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(student);
        new Thread(()->{
            try {
                int i = 1/0;
            }catch (Exception e){
                System.out.println("============");
                throw new RuntimeException(e);
            }
        }).start();


        java.time.LocalDateTime time = java.time.LocalDateTime.now();
    }*/

    /**
     * 测试Class和json属性对应数量关系
     */
    public static void test2(){
        String objectStr="{\"name_stu\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\",\"time\":\"2020-01-10 00:00:00\"}";
        String arrayStr="[{\"name_stu\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}]";

        Student student = JSON.parseObject(objectStr, Student.class);
        System.out.println(student);
    }

    public static void test3(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("sfsd");
        list.add("sfsd");
        map.put("a",list);
        String string = JSON.toJSONString(map);
        System.out.println(string);
    }

    public static void test4(){
        List<String> list = new ArrayList<>();
        list.add("sfsd");
        list.add("ssss");
        String string = JSON.toJSONString(list);
        JSONArray jsonArray = JSONArray.parseArray(string);
        System.out.println(jsonArray);
    }


}
