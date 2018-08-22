package com.luoromeo.study.gof.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 18:14
 * @modified By
 */
public class ObjectStructure {

    private List<Person> elements = new ArrayList<>();

    public void attach(Person person) {
        elements.add(person);
    }

    public void detach(Person person) {
        elements.remove(person);
    }

    public void display(Action action) {
        for (Person element : elements) {
            element.accept(action);
        }
    }
}
