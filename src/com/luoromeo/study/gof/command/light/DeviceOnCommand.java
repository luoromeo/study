package com.luoromeo.study.gof.command.light;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 15:09
 * @modified By
 */
public class DeviceOnCommand implements Command {

    private Device device;

    public DeviceOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.on();
    }
}
