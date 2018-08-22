package com.luoromeo.study.test;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月05日 09:45
 * @modified By
 */
public class TestSingleton {
    public static void main(final String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            Thread t1 = new Thread(new ThreadSingleton());
            t1.setName("thread" + i);
            t1.start();
        }

        Thread.sleep(100000);

    }

    public static class ThreadSingleton implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance();
            singleton.ha();

        }
    }
}
