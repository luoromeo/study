package com.luoromeo.study.test.concurrent.sample.bounded;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月04日 11:34
 * @modified By
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    protected BoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }
}
