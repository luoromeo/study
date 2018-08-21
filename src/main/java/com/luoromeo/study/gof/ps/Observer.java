package com.luoromeo.study.gof.ps;

public abstract class Observer {
    protected String name;

    protected Subject sub;

    public Observer(String name, Subject sub) {
        this.name = name;
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSub() {
        return sub;
    }

    public void setSub(Subject sub) {
        this.sub = sub;
    }

    public abstract void update();
}
