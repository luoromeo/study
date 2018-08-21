package com.luoromeo.study.gof.ps;

public interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyy();

    String getAction();
}
