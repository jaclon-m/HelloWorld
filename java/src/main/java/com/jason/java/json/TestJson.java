package com.jason.java.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.java.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Description
 * @author jaclon
 * @since 2021/1/14 下午3:37
 */
public class TestJson {

    public static void main(String[] args) {
//        Person person = new Person();
//        person.setAge(12);
//        person.setName("zss");
//        System.out.println(JSON.toJSONString(person));
//        System.out.println(person.toString());
//        test2();
        test4();
    }

    public static void test(){
        List<String> list  = new ArrayList<>(4);
        list.add("11");
        list.add("12");
        list.add("1ss");
        System.out.println(JSONArray.toJSONString(list));
    }

    public static void test2(){
        String str = "{\"系统号\":\"23107119\",\"小明\":\"100\",\"小张\":\"80\",\"安琪拉\":\"87\",\"宫本\":\"30\",\"兰陵王\":\"70\"}";
        System.out.println(str);
        System.out.println(JSON.toJSONString(str));
    }

    public static void test3(){
        String str = "[\"M000081\",\"M000105\",\"M000098\",\"M000099\",\"M000078\",\"M000094\",\"P0010000\",\"M000073\",\"M000104\",\"M000122\",\"M000106\",\"M000070\",\"M000095\",\"M000121\",\"E0010000\",\"M000092\",\"M000110\",\"M000089\",\"M000077\",\"M000101\",\"M000119\",\"M000083\",\"M000076\",\"M000084\",\"M000113\",\"M000115\",\"M000072\",\"M000096\",\"M000069\",\"M000100\",\"M000108\",\"M000112\",\"M000090\",\"M000088\",\"M000123\",\"M000116\",\"M000093\",\"M000080\",\"M000068\",\"M000091\",\"M000124\",\"M000109\",\"M000128\",\"M000074\",\"M000102\",\"M000111\",\"M000066\",\"M000118\",\"M000126\",\"M000079\",\"M000107\",\"M000114\",\"M000127\",\"M000082\",\"M000117\",\"M000103\",\"M000071\",\"M000075\",\"M000085\",\"M000067\",\"M000086\",\"M000120\",\"M000087\",\"M000097\",\"M000129\",\"M000032\",\"M000033\",\"M000001\",\"M000034\",\"M000035\",\"M000036\",\"M000037\",\"M000038\",\"M000039\",\"M000040\",\"M000041\",\"M000042\",\"M000043\",\"M000044\",\"M000045\",\"M000046\",\"M000047\",\"M000048\",\"M000049\",\"M000050\",\"M000051\",\"M000052\",\"M000030\",\"M000053\",\"M000054\",\"M000055\",\"M000056\",\"M000057\",\"M000058\",\"M000059\",\"M000060\",\"M000061\",\"M000062\",\"M000063\",\"M000064\",\"M000065\",\"M000149\",\"M000150\",\"M000151\",\"M000152\",\"M000153\",\"M000154\",\"M000155\",\"M000156\",\"M000157\",\"M000158\",\"M000159\",\"M000160\",\"M000161\",\"M000162\",\"M000163\",\"M000164\",\"M000165\",\"M000166\",\"M000167\",\"M000168\",\"M000169\",\"M000170\",\"M000172\",\"M000173\",\"M000174\",\"M000175\",\"M000177\",\"M000178\",\"M000179\",\"M000180\",\"M000171\",\"M000181\",\"M000182\",\"M000183\",\"M000184\",\"M000185\",\"M000186\",\"M000187\",\"M000188\",\"M000189\",\"M000190\",\"M000191\",\"M000192\",\"M000193\",\"M000194\",\"M000195\",\"M000196\",\"M000197\",\"M000198\",\"M000199\",\"M000200\",\"M000201\",\"M000202\",\"M000203\",\"M000204\",\"M000205\",\"M000206\",\"M000207\",\"M000208\",\"M000209\",\"M000210\",\"M000211\",\"M000212\",\"M000213\",\"M000214\",\"M000215\",\"M000216\",\"M000217\",\"M000218\",\"M000219\",\"M000220\",\"M000221\",\"M000222\"]";
        List<String> strs = JSON.parseArray(str, String.class);
        System.out.println("====" + strs);
    }

    public static void test4(){
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.registerModule(new Jdk8Module());
        try {
            UserDto result = objectMapper.readValue("{\"id\":\"1\", \"age\":30, \"name\":null}", UserDto.class);
            UserDto result2 = objectMapper.readValue("{\"id\":\"1\", \"age\":30}", UserDto.class);
            System.out.println(result);
            System.out.println("============");
            System.out.println(result2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //log.info("field name with null value dto:{} name:{}", result, result.getName().orElse("N/A"));
        // field name with null value dto:UserDto(id=1, name=Optional.empty, age=Optional[30]) name:N/A
        //log.info("missing field name dto:{}", objectMapper.readValue("{\"id\":\"1\", \"age\":30}", UserDto.class));
        // missing field name dto:UserDto(id=1, name=null, age=Optional[30])
    }
}
