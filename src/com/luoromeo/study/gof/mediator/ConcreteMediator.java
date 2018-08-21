package com.luoromeo.study.gof.mediator;

/**
 * @description 具体中介者类
 * @author zhanghua.luo
 * @date 2018年08月21日 10:25
 * @modified By
 */
public class ConcreteMediator extends Mediator {

    private ConcreteColleague1 concreteColleague1;
    private ConcreteColleague2 concreteColleague2;


    @Override
    public void send(String message, Colleague colleague) {
        if (colleague == concreteColleague1) {
            concreteColleague2.notifyy(message);
        } else {
            concreteColleague1.notifyy(message);
        }
    }

    public void setConcreteColleague1(ConcreteColleague1 concreteColleague1) {
        this.concreteColleague1 = concreteColleague1;
    }

    public void setConcreteColleague2(ConcreteColleague2 concreteColleague2) {
        this.concreteColleague2 = concreteColleague2;
    }
}
