package com.jaclon.mistakesOfBuz.numeralcalculations;

import java.math.BigDecimal;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/5/20 11:28
 */
public class DoubleTest {
    public static void main(String[] args) {
//        wrong1();
        wrong2();
        right();
    }

    /**
     * 对于计算机而言，0.1 无法精确表达，这是浮点数计算造成精度损失的根源。
     * 我们大都听说过 BigDecimal 类型，浮点数精确表达和运算的场景，一定要使用这个类型。
     * 使用 BigDecimal 表示和计算浮点数，且务必使用字符串的构造方法来初始化 BigDecimal。
     * 所以浮点数避坑第二原则：浮点数的字符串格式化也要通过 BigDecimal 进行。
     * BigDecimal 有 scale 和 precision 的概念，scale 表示小数点右边的位数，而 precision 表示精度，也就是有效数字的长度。
     * 对于浮点数的字符串形式输出和格式化，我们应该考虑显式进行，通过格式化表达式或格式化工具来明确小数位数和舍入方式。
     */
    private static void wrong1() {

        System.out.println(0.1 + 0.2);
        System.out.println(0.2 + 0.3);
        System.out.println(1.0 - 0.8);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);

        double amount1 = 2.15;
        double amount2 = 1.10;

        if (amount1 - amount2 == 1.05)
            System.out.println("OK");
    }

    private static void wrong2() {
        System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.2)));
        System.out.println(new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
        System.out.println(new BigDecimal(4.015).multiply(new BigDecimal(100)));
        System.out.println(new BigDecimal(123.3).divide(new BigDecimal(100)));
    }

    /**
     * 使用 BigDecimal 表示和计算浮点数，且务必使用字符串的构造方法来初始化 BigDecimal
     */
    private static void right() {
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println(new BigDecimal("1.0").subtract(new BigDecimal("0.8")));
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100")));
        System.out.println(new BigDecimal("123.3").divide(new BigDecimal("100")));

    }
}
