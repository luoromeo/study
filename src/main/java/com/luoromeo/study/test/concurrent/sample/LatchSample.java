package com.luoromeo.study.test.concurrent.sample;

import java.util.concurrent.CountDownLatch;

/**
 * @description 依旧是前面搭车的场景
 * @author zhanghua.luo
 * @date 2018年06月22日 10:38
 * @modified By
 */
public class LatchSample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new FirstBatchWorker(countDownLatch));
            t.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new SecondBatchWorker(countDownLatch));
            t.start();
        }

        while (countDownLatch.getCount() != 1) {
            Thread.sleep(100L);
        }

        System.out.println("wait for first batch finsh!");
        countDownLatch.countDown();
    }

    static class  FirstBatchWorker implements Runnable {

        private CountDownLatch countDownLatch;

        public FirstBatchWorker(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("First batch executed!");
            countDownLatch.countDown();
        }
    }

    static class SecondBatchWorker implements Runnable {

        private CountDownLatch countDownLatch;

        public SecondBatchWorker(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                System.out.println("second latch executed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
