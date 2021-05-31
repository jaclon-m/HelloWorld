package com.jaclon.mistakesOfBuz.exception;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/5/31 18:24
 */
public class TestResource implements AutoCloseable {

    public void read() throws Exception {
        throw new Exception("read error");
    }

    @Override
    public void close() throws Exception {
        throw new Exception("close error");
    }
}
