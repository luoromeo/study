package com.luoromeo.study.gof.state.work;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 11:10
 * @modified By
 */
public class Work {

    private State state;

    private double hour;

    private boolean finish = false;

    public Work() {
        state = new ForenoonState();

    }

    public void writeProgram() {
        state.wirteProgram(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
