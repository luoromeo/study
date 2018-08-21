package com.luoromeo.study.gof.command;

/**
 * @description 用来声明执行操作的接口
 * @author zhanghua.luo
 * @date 2018年08月21日 14:37
 * @modified By
 */
public abstract class Command {

    protected Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    abstract public void execute();
}
