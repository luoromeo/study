package com.luoromeo.study.gof.state.work;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 11:22
 * @modified By
 */
public class RestState implements State {
    @Override
    public void wirteProgram(Work w) {
        System.out.printf("当前时间: %s点 下班回家了 \n", w.getHour());
    }
}
