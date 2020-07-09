/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.validator.example;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Classname ValidPassengerCountValidator
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/26
 */
public class ValidPassengerCountValidator
        implements ConstraintValidator<ValidPassengerCount, Car> {

    @Override
    public void initialize(ValidPassengerCount constraintAnnotation) {
    }

    @Override
    public boolean isValid(Car car, ConstraintValidatorContext constraintValidatorContext) {
        if ( car == null ) {
            return true;
        }

        boolean isValid = car.getPassengers().size() <= car.getSeatCount();
        isValid = false;
        if ( !isValid ) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate( "{my.custom.template}" )
                    .addPropertyNode( "passengers" ).addConstraintViolation();
        }

        return isValid;
    }
}
