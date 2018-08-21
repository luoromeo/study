package com.luoromeo.study.gof.command;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 14:40
 * @modified By
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
