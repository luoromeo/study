package com.luoromeo.study.gof.command.light;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 15:13
 * @modified By
 */
public class Test {

    public static void main(String[] args) {
        Light light = new Light();
        Command on = new DeviceOnCommand(light);
        Switch s = new Switch(on);
        s.click();


        Switch ss = new Switch(() -> new Light().off());
        ss.click();

    }


}
