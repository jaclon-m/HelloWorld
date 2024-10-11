package com.jaclon.spring.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2024/10/9 17:35
 */
public interface LogService {
    /**
     * propagation设置事务属性：传播行为设置为当前操作需要新事务
     * @param out
     * @param in
     * @param money
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void log(String out, String in, Double money);
}
