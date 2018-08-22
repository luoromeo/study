package com.luoromeo.study.gof.state;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 10:32
 * @modified By
 */
public class ConcreteStateA implements State{


    @Override
    public void handle(Context context) {
        System.out.println("设置状态为B");
        context.setState(new ConcreteStateB());
    }
}
