package com.jaclon.spring.project.example.aop3;

/**
 * @author jaclon
 * @date 2020/12/6
 */
public class DaoImpl implements Dao {

    @Override
    public void select() {
        System.out.println("Enter DaoImpl.select()");
    }

    @Override
    public void insert() {
        System.out.println("Enter DaoImpl.insert()");
    }

}