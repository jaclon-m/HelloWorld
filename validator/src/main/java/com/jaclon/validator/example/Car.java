/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.validator.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Classname Car
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @NotNull
    private String manufacturer;

//    @Size(
//            min = 2,
//            max = 14,
//            message = "The license plate '${validatedValue}' must be between {min} and {max} characters long"
//    )
//    private String licensePlate;

    @Min(
            value = 2,
            message = "There must be at least {value} seat${value > 1 ? 's' : ''}"
    )
    private int seatCount;

//    @DecimalMax(
//            value = "350",
//            message = "The top speed ${formatter.format('%1$.2f', validatedValue)} is higher " +
//                    "than {value}"
//    )
//    private double topSpeed;

//    @DecimalMax(value = "100000", message = "Price must not be higher than ${value}")
//    private BigDecimal price;

    private List<Person> passengers;

}
