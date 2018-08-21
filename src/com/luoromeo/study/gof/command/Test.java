package com.luoromeo.study.gof.command;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 14:42
 * @modified By
 */
public class Test {

    public static void main(String[] args) {
        //电灯
        Receiver receiver = new Receiver();
        //命令
        Command c = new ConcreteCommand(receiver);
        //开关
        Invoker i = new Invoker();
        i.setCommand(c);

        i.executeCommand();

    }

}
