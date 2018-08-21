package com.luoromeo.study.test.concurrent.sample.bounded;

/**
 * @description 第一个简单的有界缓存实现。 put和take方法都进行了同步以确保实现对缓存状态的独占访问，
 *              因为这两个方法在访问缓存时都采用"先检查，再运行"的逻辑策略。
 * @author zhanghua.luo
 * @date 2018年07月04日 10:43
 * @modified By
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public GrumpyBoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws Exception {
        if (isFull()) {
            throw new Exception("BUFFER FULL");
        }
        doPut(v);
    }

    public synchronized V take() throws Exception {
        if (isEmpty()) {
            throw new Exception("BUFFER EMPTY");
        }
        return doTake();
    }

}
