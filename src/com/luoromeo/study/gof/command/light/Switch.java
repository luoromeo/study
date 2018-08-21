package com.luoromeo.study.gof.command.light;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 15:07
 * @modified By
 */
public class Switch {

    private Command command;

    public Switch(Command command) {
        this.command = command;
    }

    public void click() {
        command.execute();
    }
}
