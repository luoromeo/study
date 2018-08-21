package com.luoromeo.study.gof.command.light;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 15:12
 * @modified By
 */
public class DeviceOffCommand implements Command {

    private Device device;

    public DeviceOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.off();
    }
}
