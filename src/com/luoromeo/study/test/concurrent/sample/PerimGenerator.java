package com.luoromeo.study.test.concurrent.sample;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月22日 13:50
 * @modified By
 */
public class PerimGenerator implements Runnable {

    private final List<BigInteger> primes = new ArrayList<>();

    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            System.out.println(p = p.nextProbablePrime());
            synchronized (this) {
                primes.add(p);
            }
        }

    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }

    public static void main(String[] args) {
        PerimGenerator perimGenerator = new PerimGenerator();
        new Thread(perimGenerator).start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            perimGenerator.cancel();
        }
    }
}
