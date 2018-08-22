package com.luoromeo.study.gof.state;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 10:36
 * @modified By
 */
public class Test {

    public static void main(String[] args) {
        Context c = new Context(new ConcreteStateA());

        c.request();
        c.request();
        c.request();
        c.request();
    }
}
