package com.luoromeo.study.gof.ps;

import java.util.ArrayList;
import java.util.List;

/**
 * 前台秘书类
 */
public class Secretary implements Subject {

    //同事列表
    private List<Observer> observers = new ArrayList<>();

    private String action;

    //增加
    public void attach(Observer observer) {
        observers.add(observer);
    }

    //通知
    public void notifyy() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
