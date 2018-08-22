package com.luoromeo.study.test.concurrent.sample.puzzles;

import java.util.concurrent.CountDownLatch;

/**
 * @description 携带结果的一种闭锁实现方案
 * @author zhanghua.luo
 * @date 2018年06月28日 11:22
 * @modified By
 */
public class ValueLatch<T> {

    private T value = null;

    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return done.getCount() == 0;
    }

    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }
}
