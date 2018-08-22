package com.luoromeo.study.test.concurrent.sample;

import java.util.concurrent.Semaphore;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月22日 10:23
 * @modified By
 */
public class AbnormalSemaphoreSample {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new MyWorker(semaphore));
            t.start();
        }

        System.out.println("Action..GO!");
        semaphore.release(5);
        System.out.println("Wait for permit off");
        while (semaphore.availablePermits() != 0) {
            Thread.sleep(100L);
        }
        System.out.println("Action..GO again!");
        semaphore.release(5);
    }

    static class MyWorker implements Runnable {

        private Semaphore semaphore;

        public MyWorker(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Executed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
