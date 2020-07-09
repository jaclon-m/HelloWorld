/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.validator.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Classname MyAnnotation
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/18
 */
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyAnnotationValid.class)
@Documented
public @interface MyAnnotation {
    /**
     * 注解赋值--字段
     * @return
     */
    String value() ;

    String field();

    /**
     * 必须的属性
     * 显示 校验信息
     * 利用 {} 获取 属性值，参考了官方的message编写方式
     *@see org.hibernate.validator 静态资源包里面 message 编写方式
     */
    String message() default "校验不通过";

    /**
     * 必须的属性
     * 用于分组校验
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
