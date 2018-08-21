package com.luoromeo.study.test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月17日 16:54
 * @modified By
 */
public class ReferenceTest {

    public void test() {
        Object counter = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference p = new PhantomReference<>(counter, referenceQueue);
        counter = null;
        try {
            //Remove是一个阻塞方法，可以指定timeOut，或者一直阻塞
            Reference ref = referenceQueue.remove(1000L);
            if(ref != null) {
                //dosomething
            }
        } catch (InterruptedException ignore) {

        }

    }
}
