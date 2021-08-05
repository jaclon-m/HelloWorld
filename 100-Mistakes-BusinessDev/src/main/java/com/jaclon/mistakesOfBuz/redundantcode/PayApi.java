package com.jaclon.mistakesOfBuz.redundantcode;

import lombok.Data;

import java.math.BigDecimal;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/5 20:12
 */
@BankApi(url = "/bank/pay", desc = "支付接口")
@Data
public class PayApi implements AbstractApi {
    @BankApiField(order = 1, type = "N", length = 20)
    private long userId;
    @BankApiField(order = 2, type = "M", length = 10)
    private BigDecimal amount;
}
