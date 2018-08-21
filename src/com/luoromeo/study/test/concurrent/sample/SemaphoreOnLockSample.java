package com.luoromeo.study.test.concurrent.sample;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月04日 16:40
 * @modified By
 */
public class SemaphoreOnLockSample {

    public static void main(String[] args) {
        SemaphoreOnLock semaphore = new SemaphoreOnLock(5);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new SemaphoreOnLockSample.Worker(semaphore));
            t.start();
        }
        System.out.println("Action..GO!");
    }

    static class Worker implements Runnable {
        private SemaphoreOnLock semaphoreOnLock;

        public Worker(SemaphoreOnLock semaphoreOnLock) {
            this.semaphoreOnLock = semaphoreOnLock;
        }

        @Override
        public void run() {
            try {
                semaphoreOnLock.acquire();
                System.out.println("Executed!");
                Thread.sleep(5000);
                semaphoreOnLock.release();
            } catch (InterruptedException ignore) {
            }
        }
    }
}
