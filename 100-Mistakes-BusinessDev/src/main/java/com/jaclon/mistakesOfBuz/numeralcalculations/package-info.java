/**
 * 第一，切记，要精确表示浮点数应该使用 BigDecimal。并且，使用 BigDecimal 的 Double 入参的构造方法同样存在精度丢失问题，应该使用
 * String 入参的构造方法或者 BigDecimal.valueOf 方法来初始化。
 * 第二，对浮点数做精确计算，参与计算的各种数值应该始终使用 BigDecimal，所有的计算都要通过 BigDecimal 的方法进行，切勿只是让 BigDecimal
 * 来走过场。任何一个环节出现精度损失，最后的计算结果可能都会出现误差。
 * 第三，对于浮点数的格式化，如果使用 String.format的话，需要认识到它使用的是四舍五入，可以考虑使用 DecimalFormat 来明确指定舍入方式。
 * 但考虑到精度问题，我更建议使用 BigDecimal 来表示浮点数，并使用其 setScale方法指定舍入的位数和方式。
 * 第四，进行数值运算时要小心溢出问题，虽然溢出后不会出现异常，但得到的计算结果是完全错误的。我们考虑使用
 * Math.xxxExact 方法来进行运算，在溢出时能抛出异常，更建议对于可能会出现溢出的大数运算使用 BigInteger 类。
 *
 * TODO money类
 * @author jaclon
 * @since 2021/5/20 11:28
 */
package com.jaclon.mistakesOfBuz.numeralcalculations;