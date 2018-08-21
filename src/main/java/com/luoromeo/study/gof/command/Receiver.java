package com.luoromeo.study.gof.command;

/**
 * @description Receiver类 知道如何实施与执行一个与请求相关的操作，任何类都可能作为一个接收者
 * @author zhanghua.luo
 * @date 2018年08月21日 14:38
 * @modified By
 */
public class Receiver {

    public void action() {
        System.out.println("执行请求!");
    }
}
