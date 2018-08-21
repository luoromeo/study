package com.luoromeo.study.test.concurrent.sample;

import java.util.concurrent.Semaphore;

/**
 * @description 典型的Semaphore实例 模拟以下场景:
 *              在车站，机场等出租车时，当很多空出租车就位时，为防止过度拥挤，调度员指挥排队等待坐车的队伍一次进来5个人，
 *              等这五个人坐车出发，再放下一批过去。 但是该实例并不符合场景，本例是1人出发了，立即就有排队的人获得许可。
 * @author zhanghua.luo
 * @date 2018年06月22日 10:08
 * @modified By
 */
public class UsualSemaphoreSample {

    public static void main(String[] args) {
        System.out.println("Action...GO!");
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new SemaphoreWorker(semaphore));
            t.start();

        }
    }

    static class SemaphoreWorker implements Runnable {

        private String name;

        private Semaphore semaphore;

        public SemaphoreWorker(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                log("is waiting for permit");
                semaphore.acquire();
                log("acquire a permit");
                log("executed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log("relased a permit");
                semaphore.release();
            }
        }

        private void log(String msg) {

            if (name == null) {
                name = Thread.currentThread().getName();
            }

            System.out.println(name + " " + msg);

        }
    }

}
