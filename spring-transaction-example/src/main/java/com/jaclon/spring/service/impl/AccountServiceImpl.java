package com.jaclon.spring.service.impl;

import com.jaclon.spring.dao.AccountDao;
import com.jaclon.spring.service.AccountService;
import com.jaclon.spring.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2024/10/9 15:42
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private LogService logService;

    @Override
    public void transfer(String out,String in ,Double money) {
        try {
            accountDao.outMoney(out, money);
            int i = 1 / 0;
            accountDao.inMoney(in, money);
        } finally {
            logService.log(out, in, money);
        }
    }
}
