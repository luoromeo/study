package com.luoromeo.study.gof.mediator;

/**
 * @description 抽象同事类
 * @author zhanghua.luo
 * @date 2018年08月21日 10:22
 * @modified By
 */
public abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}
