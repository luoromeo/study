package com.luoromeo.study.gof.state.work;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 11:24
 * @modified By
 */
public class SleepState implements State {
    @Override
    public void wirteProgram(Work w) {
        System.out.printf("当前时间:%s点，不行了，睡着了。\n", w.getHour());
    }
}
