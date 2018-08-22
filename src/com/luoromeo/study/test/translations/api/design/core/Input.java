package com.luoromeo.study.test.translations.api.design.core;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月24日 10:53
 * @modified By
 */
public interface Input<T, SenderThrowableType extends Throwable> {
    <ReceiverThrowableType extends Throwable> void transferTo(Output<T, ReceiverThrowableType> output)
            throws SenderThrowableType, ReceiverThrowableType;
}
