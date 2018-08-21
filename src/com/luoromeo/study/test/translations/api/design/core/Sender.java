package com.luoromeo.study.test.translations.api.design.core;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月24日 11:09
 * @modified By
 */
public interface Sender<T, SendThrowableType extends Throwable> {
    <ReceiverThrowableType extends Throwable> void sendTo(Receiver<T, ReceiverThrowableType> receiver)
            throws ReceiverThrowableType, SendThrowableType;

}
