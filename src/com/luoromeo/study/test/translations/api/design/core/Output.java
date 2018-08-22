package com.luoromeo.study.test.translations.api.design.core;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月24日 10:58
 * @modified By
 */
public interface Output<T, ReceiverThrowableType extends Throwable> {
    <SendThrowableType extends Throwable> void receiveFrom(Sender<T, SendThrowableType> sender) throws ReceiverThrowableType, SendThrowableType;
}
