package com.jaclon.mistakesOfBuz.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/5/31 18:25
 */
@Slf4j
public class FinallyIssueTest {
    public static void main(String[] args) throws Exception {
        FinallyIssueTest test = new FinallyIssueTest();
//        test.useresourcewrong();
        test.useresourceright();
//        test.wrong();
//        test.right2();

    }
    public void useresourcewrong() throws Exception {
        TestResource testResource = new TestResource();
        try {
            testResource.read();
        } finally {
            testResource.close();
        }
    }

    /**
     * 实现了 AutoCloseable 接口的资源，建 议使用 try-with-resources 来释放资源
     *
     * @throws Exception
     */
    public void useresourceright() throws Exception {
        try (TestResource testResource = new TestResource()) {
            testResource.read();
        }
    }

    public void wrong() {
        try {
            log.info("try");
            throw new RuntimeException("try");
        } finally {
            log.info("finally");
            throw new RuntimeException("finally");
        }
    }

    public void right() {
        try {
            log.info("try");
            throw new RuntimeException("try");
        } finally {
            log.info("finally");
            try {
                throw new RuntimeException("finally");
            } catch (Exception ex) {
                log.error("finally", ex);
            }
        }
    }

    public void right2() throws Exception {
        Exception e = null;
        try {
            log.info("try");
            throw new RuntimeException("try");
        } catch (Exception ex) {
            e = ex;
        } finally {
            log.info("finally");
            try {
                throw new RuntimeException("finally");
            } catch (Exception ex) {
                if (e != null) {
                    e.addSuppressed(ex);
                } else {
                    e = ex;
                }
            }
        }
        throw e;
    }
}
