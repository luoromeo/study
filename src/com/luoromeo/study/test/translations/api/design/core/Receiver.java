package com.luoromeo.study.test.translations.api.design.core;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月24日 11:10
 * @modified By
 */
public interface Receiver<T, ReceiverThrowableType extends Throwable> {
    void receive(T item) throws ReceiverThrowableType;

    void finished() throws ReceiverThrowableType;
}
