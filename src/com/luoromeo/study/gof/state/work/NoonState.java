package com.luoromeo.study.gof.state.work;

/**
 * @description 中午工作状态
 * @author zhanghua.luo
 * @date 2018年08月22日 11:15
 * @modified By
 */
public class NoonState implements State {
    @Override
    public void wirteProgram(Work w) {
        if (w.getHour() < 13) {
            System.out.printf("当前时间:%s点，饿了，午饭，犯困，午休. \n", w.getHour());
        } else {
            //超过13点转入下午工作状态
            w.setState(new AfternoonState());
            w.writeProgram();
        }
    }
}
