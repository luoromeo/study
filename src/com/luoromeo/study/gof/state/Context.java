package com.luoromeo.study.gof.state;

/**
 * @description 维护一个ConcreteState子类的实例，这个实例定义当前的状态
 * @author zhanghua.luo
 * @date 2018年08月22日 10:31
 * @modified By
 */
public class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void request() {
        state.handle(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
