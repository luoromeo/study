package com.luoromeo.study.gof.state.work;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 11:17
 * @modified By
 */
public class AfternoonState implements State {
    @Override
    public void wirteProgram(Work w) {
        if (w.getHour() < 17) {
            System.out.printf("当前时间:%s点 下午状态还不错 继续努力 \n", w.getHour());
        } else {
            w.setState(new EveningState());
            w.writeProgram();
        }
    }
}
