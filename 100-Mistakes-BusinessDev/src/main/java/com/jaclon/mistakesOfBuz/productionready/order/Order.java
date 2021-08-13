package com.jaclon.mistakesOfBuz.productionready.order;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/12 15:11
 */
@Data
public class Order implements Serializable {
    private Long id;
    private Long userId;
    private Long merchantId;
}