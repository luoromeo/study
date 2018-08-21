package com.luoromeo.study.test.concurrent.sample;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月25日 09:59
 * @modified By
 */
public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!isInterrupted()) {
                System.out.println(p = p.nextProbablePrime());
                queue.put(p);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
