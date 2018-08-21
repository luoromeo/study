package com.luoromeo.study.test.concurrent.sample;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月04日 14:54
 * @modified By
 */
public class ThreadGate {
    private boolean isOpen;

    private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation) {
            wait();
        }
    }
}
