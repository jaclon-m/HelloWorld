package com.jaclon.validator;

import com.jaclon.validator.example.Car;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ValidatorApplicationTests {
    private Validator validator;

    public static void main(String[] args) {
    }
    public void getValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void test() {
//        Car car = new Car( null, "A", 1, 400.123456, BigDecimal.valueOf( 200000 ) );
//
//        String message = validator.validateProperty( car, "manufacturer" )
//                .iterator()
//                .next()
//                .getMessage();
//        assertEquals( "must not be null", message );
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

}
