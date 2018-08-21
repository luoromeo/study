package com.luoromeo.study.test.concurrent.sample;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月25日 13:52
 * @modified By
 */
public class CancelExecutorSample {

    private static final ExecutorService cancelExecutor = Executors.newFixedThreadPool(2);

    public static void timedRun(Runnable r, long timeout, TimeUnit time) throws InterruptedException {

        Future<?> task = cancelExecutor.submit(r);
        try {
            task.get(timeout, time);
        } catch (TimeoutException | ExecutionException e) {

        } finally {
            task.cancel(true);
        }

    }

    public static void main(String[] args) {
        BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(10);
        BrokenPrimeProducer primeProducer = new BrokenPrimeProducer(queue);
        primeProducer.start();
        try {
            Thread.sleep(10000L);
            primeProducer.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
