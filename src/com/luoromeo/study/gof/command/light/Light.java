package com.luoromeo.study.gof.command.light;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 15:06
 * @modified By
 */
public class Light implements Device {

    @Override
    public void on() {
        System.out.println("开灯");
    }

    @Override
    public void off() {
        System.out.println("关灯");
    }
}
