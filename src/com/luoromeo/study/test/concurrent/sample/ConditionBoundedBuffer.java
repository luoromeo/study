package com.luoromeo.study.test.concurrent.sample;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月04日 15:47
 * @modified By
 */
public class ConditionBoundedBuffer<T> {

    private Integer BUFFER_SIZE = 16;

    private final Lock lock = new ReentrantLock();

    // 条件谓词: notFull (count < items.length)
    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    private final T[] items = (T[]) new Object[BUFFER_SIZE];

    private int tail, head, count;

    public ConditionBoundedBuffer(Integer size) {
        this.BUFFER_SIZE = size;
    }

    public void put(T t) throws InterruptedException {

        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[tail] = t;
            if (++tail == items.length) {
                tail = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T t = items[head];
            if (++head == items.length) {
                head = 0;
            }
            --count;
            notFull.signal();
            return t;

        } finally {
            lock.unlock();
        }
    }
}
