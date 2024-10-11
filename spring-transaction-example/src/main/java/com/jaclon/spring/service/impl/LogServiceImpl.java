package com.jaclon.spring.service.impl;

import com.jaclon.spring.dao.LogDao;
import com.jaclon.spring.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2024/10/9 17:36
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void log(String out, String in, Double money ) {
        logDao.log("转账操作由"+out+"到"+in+",金额："+money);
    }
}
