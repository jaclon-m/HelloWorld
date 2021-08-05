package com.jaclon.mistakesOfBuz.redundantcode;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 反射方式
 *
 * @author jaclon
 * @since 2021/8/5 20:13
 */
@Slf4j
public class ReflectService {
    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
        CreateUserApi createUserApi = new CreateUserApi();
        createUserApi.setName(name);
        createUserApi.setIdentity(identity);
        createUserApi.setAge(age);
        createUserApi.setMobile(mobile);
        return remoteCall(createUserApi);
    }

    public static String pay(long userId, BigDecimal amount) throws IOException {
        PayApi payApi = new PayApi();
        payApi.setUserId(userId);
        payApi.setAmount(amount);
        return remoteCall(payApi);
    }

    private static String remoteCall(AbstractApi api) throws IOException {
        //从BankApi注解获取请求地址
        BankApi bankApi = api.getClass().getAnnotation(BankApi.class);
        bankApi.url();
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(api.getClass().getDeclaredFields()) //获得所有字段
                .filter(field -> field.isAnnotationPresent(BankApiField.class)) //查找标记了注解的字段
                .sorted(Comparator.comparingInt(a -> a.getAnnotation(BankApiField.class).order())) //根据注解中的order对字段排序
                .peek(field -> field.setAccessible(true)) //设置可以访问私有字段
                .forEach(field -> {
                    //获得注解
                    BankApiField bankApiField = field.getAnnotation(BankApiField.class);
                    Object value = "";
                    try {
                        //反射获取字段值
                        value = field.get(api);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    //根据字段类型以正确的填充方式格式化字符串
                    switch (bankApiField.type()) {
                        case "S": {
                            stringBuilder.append(String.format("%-" + bankApiField.length() + "s", value.toString()).replace(' ', '_'));
                            break;
                        }
                        case "N": {
                            stringBuilder.append(String.format("%" + bankApiField.length() + "s", value.toString()).replace(' ', '0'));
                            break;
                        }
                        case "M": {
                            if (!(value instanceof BigDecimal))
                                throw new RuntimeException(String.format("{} 的 {} 必须是BigDecimal", api, field));
                            stringBuilder.append(String.format("%0" + bankApiField.length() + "d", ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
                            break;
                        }
                        default:
                            break;
                    }
                });
        //签名逻辑
        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
        String param = stringBuilder.toString();
        long begin = System.currentTimeMillis();
        //发请求
        String result = Request.Post("http://localhost:45678/reflection" + bankApi.url())
                .bodyString(param, ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        log.info("调用银行Api {} url:{} 参数:{} 耗时:{}ms", bankApi.desc(), bankApi.url(), param, System.currentTimeMillis() - begin);
        return result;
    }
}
