package com.luoromeo.study.test.concurrent.sample;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月25日 14:05
 * @modified By
 */
public interface CancellableTask<T> extends Callable<T> {

    void cancel();

    RunnableFuture<T> newTask();

}
