package com.luoromeo.study.gof.state.work;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 11:12
 * @modified By
 */
public class ForenoonState implements State {
    @Override
    public void wirteProgram(Work w) {
        if (w.getHour() < 12) {
            System.out.printf("当前时间:%s点 上午工作，精神百倍 \n", w.getHour());
        } else {
            //超过12点转入中午工作状态
            w.setState(new NoonState());
            w.writeProgram();
        }
    }
}
