/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.validator.controller;

import com.jaclon.validator.entity.Person;
import com.jaclon.validator.entity.SubmitRequestDetailInfo;
import com.jaclon.validator.service.SubmitRequestDetailInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * @Classname HelloController
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/18
 */
@RestController
@Slf4j
public class HelloController {
    @Autowired
    private Validator hibernateValidator;

    @Autowired
    private SubmitRequestDetailInfoService submitRequestDetailInfoService;

    @GetMapping("/hello")
    public String Hello(){
        List<SubmitRequestDetailInfo> list = submitRequestDetailInfoService.list();
        SubmitRequestDetailInfo detailInfo = list.get(0);
        validate(detailInfo);
        return "Hello world";
    }

    @PostMapping("/response")
    public String testError(@Validated @RequestBody Person person){
        System.out.println(person);
        return "success";
    }

    /**
     * 校验
     *
     * @param target 校验对象
     * @param groups 校验组，默认不填
     * @return 校验结果
     */
    public  <T> String validate(T target, Class<?>... groups) {

        Set<ConstraintViolation<T>> constraints = hibernateValidator.validate(target, groups);
        boolean success = CollectionUtils.isEmpty(constraints);
        String message = null;
        if (!success) {
            Iterator<ConstraintViolation<T>> iterator = constraints.iterator();
            while (iterator.hasNext()){
                ConstraintViolation<T> constraint = iterator.next();
                message = constraint.getMessage();
                log.info("=========" + message);
                log.info(constraint.getMessageTemplate());
            }

        }
        return message;
    }

}
