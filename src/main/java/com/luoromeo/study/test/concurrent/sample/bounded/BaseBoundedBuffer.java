package com.luoromeo.study.test.concurrent.sample.bounded;

/**
 * @description 有界缓存实现的基类
 * @author zhanghua.luo
 * @date 2018年07月04日 10:31
 * @modified By
 */
public abstract class BaseBoundedBuffer<V> {

    private final V[] buf;

    private int tail;

    private int head;

    private int count;

    protected BaseBoundedBuffer(int capacity) {
        this.buf = (V[]) new Object[capacity];
    }

    protected synchronized final void doPut(V v) {
        buf[tail] = v;
        if (++tail == buf.length) {
            tail = 0;
        }
        ++count;
    }

    protected synchronized final V doTake() {
        V v = buf[head];
        buf[head] = null;
        if (++head == buf.length) {
            head = 0;
        }
        --count;
        return v;
    }

    public synchronized final boolean isFull() {
        return count == buf.length;
    }

    public synchronized final boolean isEmpty() {
        return count == 0;
    }
}
