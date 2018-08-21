package com.luoromeo.study.gof.mediator;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 10:25
 * @modified By
 */
public class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        // 发送信息通常是中介者发送出去的
        mediator.send(message, this);
    }

    public void notifyy(String message) {
        System.out.println("同事1收到消息!" + message);
    }
}
