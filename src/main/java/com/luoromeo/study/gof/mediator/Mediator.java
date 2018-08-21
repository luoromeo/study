package com.luoromeo.study.gof.mediator;

/**
 * @description 抽象中介者类
 * @author zhanghua.luo
 * @date 2018年08月21日 10:22
 * @modified By
 */
public abstract class Mediator {

    /**
     * @description 发送消息方法，得到同事对象和发送信息
     * @author zhanghua.luo
     * @date 2018年08月21日 10:23:41
     * @param message
     * @param colleague
     * @return
     */
    public abstract void send(String message, Colleague colleague);
}
