package com.luoromeo.study.gof.mediator;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 10:58
 * @modified By
 */
public class Test {
    public static void main(String[] args) {
        ConcreteMediator m = new ConcreteMediator();
        ConcreteColleague1 c1 = new ConcreteColleague1(m);
        ConcreteColleague2 c2 = new ConcreteColleague2(m);

        m.setConcreteColleague1(c1);
        m.setConcreteColleague2(c2);

        c1.send("吃过饭没有!");
        c2.send("没有呢，你打算请客?");

    }
}
