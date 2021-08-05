package com.jaclon.mistakesOfBuz.redundantcode;

import lombok.Data;

/**
 *
 * @author jaclon
 * @since 2021/8/5 20:10
 */
@BankApi(url = "/bank/createUser", desc = "创建用户接口")
@Data
public class CreateUserApi implements AbstractApi{
    @BankApiField(order = 1, type = "S", length = 10)
    private String name;
    @BankApiField(order = 2, type = "S", length = 18)
    private String identity;
    @BankApiField(order = 4, type = "S", length = 11)
    private String mobile;
    @BankApiField(order = 3, type = "N", length = 5)
    private int age;
}
