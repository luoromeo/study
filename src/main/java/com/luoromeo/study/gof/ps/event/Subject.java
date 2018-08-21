package com.luoromeo.study.gof.ps.event;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Event> observers = new ArrayList<>();

    public void addObserver(String method, Object obj, Object[] params) {
        this.observers.add(new Event(method, obj, params));
    }

    public void notifyObserver() {
        for (Event observer : observers) {
            try {
                observer.invoker();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
