package com.jason.java.thread.current;

/**
 * @author jaclon
 * @date 2019/7/13
 * @time 8:35
 */
public class TestVolatile {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        Thread thread = new Thread(threadDemo);
        thread.start();
        while (true){
            if(threadDemo.isFlag()){
                System.out.println("----------------");
            }
        }
    }
}

class ThreadDemo implements Runnable{

    private /*volatile*/ boolean flag;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setFlag(true);
        System.out.println("flag=======:" + isFlag());

    }
}
