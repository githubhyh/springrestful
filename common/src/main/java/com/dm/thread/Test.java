package com.dm.thread;

import java.util.concurrent.Semaphore;

/**
 * thread中的run和start区别
 * start启动线程（线程就绪状态，等待时间片）
 * run执行run方法体，属于runnable接口中的方法，只是方法调用，还是在main线程中运行
 * */
public class Test {
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello world");
        }).start();

        new Thread(()->{
            System.out.println("hello world1");
        }).start();

        System.out.println("test");
    }

    public void test() throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        semaphore.acquire();

        //do something

    }
}
