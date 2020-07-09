/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.validator.example;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Classname TestCar
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/25
 */
public class TestCar {

    private Validator validator;
    public static void main(String[] args) {

    }

    @Before
    public void getValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void test1(){
//        Car car = new Car( null, "A", 1, 400.123456, BigDecimal.valueOf( 200000 ) );
//
//        String message = validator.validateProperty( car, "manufacturer" )
//                .iterator()
//                .next()
//                .getMessage();
//        assertEquals( "不能为null", message );
//
//        message = validator.validateProperty( car, "licensePlate" )
//                .iterator()
//                .next()
//                .getMessage();
//        assertEquals(
//                "The license plate 'A' must be between 2 and 14 characters long",
//                message
//        );
//
//        message = validator.validateProperty( car, "seatCount" ).iterator().next().getMessage();
//        assertEquals( "There must be at least 2 seats", message );
//
//        message = validator.validateProperty( car, "topSpeed" ).iterator().next().getMessage();
//        assertEquals( "The top speed 400.12 is higher than 350", message );
//
//        message = validator.validateProperty( car, "price" ).iterator().next().getMessage();
//        assertEquals( "Price must not be higher than $100000", message );
    }

    @Test
    public void test2(){
        List<Person> list = new ArrayList<>(2);
        Car car = new Car("sfsd", 23, list);
        validator.validate(car);
    }
}
