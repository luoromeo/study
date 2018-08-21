package com.luoromeo.study.gof.command;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 14:42
 * @modified By
 */
public class ConcreteCommand extends Command {

    public ConcreteCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
