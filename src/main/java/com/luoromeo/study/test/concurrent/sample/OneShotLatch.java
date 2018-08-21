package com.luoromeo.study.test.concurrent.sample;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月05日 10:34
 * @modified By
 */
public class OneShotLatch {

    private final Sync sync = new Sync();

    public void singal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedLongSynchronizer {
        @Override
        protected long tryAcquireShared(long arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(long arg) {
            setState(1);
            return true;
        }
    }

}
