package com.luoromeo.study.gof.mediator;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 10:25
 * @modified By
 */
public class ConcreteColleague2  extends Colleague{
    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public void notifyy(String message) {
        System.out.println("同事2收到消息!" + message);

    }
}
