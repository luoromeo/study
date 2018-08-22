package com.luoromeo.study.gof.state.work;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 11:36
 * @modified By
 */
public class Test {

    public static void main(String[] args) {
        Work w = new Work();
        w.setHour(9);
        w.writeProgram();
        w.setHour(10);
        w.writeProgram();
        w.setHour(12);
        w.writeProgram();
        w.setHour(13);
        w.writeProgram();
        w.setHour(14);
        w.writeProgram();
        w.setHour(17);
        w.setFinish(true);
        w.writeProgram();
    }
}
