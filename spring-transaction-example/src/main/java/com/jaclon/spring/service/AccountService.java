package com.jaclon.spring.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2024/10/9 15:40
 */
public interface AccountService {
    /**
     * 转账操作
     * 配置当前接口方法具有事务
     * @param out 传出方
     * @param in 转入方
     * @param money 金额
     */
    @Transactional
    public void transfer(String out,String in ,Double money) ;

}
