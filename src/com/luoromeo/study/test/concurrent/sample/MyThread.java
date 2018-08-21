package com.luoromeo.study.test.concurrent.sample;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月25日 15:03
 * @modified By
 */
public class MyThread extends Thread {


    public MyThread(String name) {
        super(name);
    }


    @Override
    public void run() {
        try {
            int i = 0;
            while (!isInterrupted()) {
                Thread.sleep(100); // 休眠100ms
                i++;
                System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
        }
    }
}
