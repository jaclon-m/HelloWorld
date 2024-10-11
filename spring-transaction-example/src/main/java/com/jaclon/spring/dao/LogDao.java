package com.jaclon.spring.dao;

import org.apache.ibatis.annotations.Insert;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2024/10/9 17:35
 */
public interface LogDao {
    @Insert("insert into tbl_log (info,create_date) values(#{info},now())")
    void log(String info);
}
