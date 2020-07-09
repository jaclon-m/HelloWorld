/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.validator.validator;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorInitializationContext;

import javax.validation.ConstraintValidatorContext;
import javax.validation.metadata.ConstraintDescriptor;
import java.time.Instant;

/**
 * @Classname MyHValidator
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/26
 */
public class MyHValidator implements HibernateConstraintValidator<MyAnnotation, Instant> {
    @Override
    public boolean isValid(Instant instant, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

    @Override
    public void initialize(ConstraintDescriptor<MyAnnotation> constraintDescriptor, HibernateConstraintValidatorInitializationContext initializationContext) {

    }
}
