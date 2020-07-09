/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.validator.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Classname MyAnnotationValid
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/18
 */
@Slf4j
public class MyAnnotationValid implements ConstraintValidator<MyAnnotation,String> {

    @Override
    public void initialize(MyAnnotation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("my customer Connstraint message")
                .addConstraintViolation();
        return false;
    }
}
