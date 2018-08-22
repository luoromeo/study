package com.luoromeo.study.gof.state.work;

/**
 * @description 晚间工作状态
 * @author zhanghua.luo
 * @date 2018年08月22日 11:19
 * @modified By
 */
public class EveningState implements State {
    @Override
    public void wirteProgram(Work w) {
        if (w.isFinish()) {
            w.setState(new RestState());
            w.writeProgram();
        } else {
            if (w.getHour() < 21) {
                System.out.printf("当前时间:%s点 加班哦，疲累之极 \n", w.getHour());
            } else {
                w.setState(new SleepState());
                w.writeProgram();
            }
        }
    }
}
