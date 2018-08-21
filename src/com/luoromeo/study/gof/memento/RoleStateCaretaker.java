package com.luoromeo.study.gof.memento;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 17:54
 * @modified By
 */
public class RoleStateCaretaker {

    private RoleStateMemento memento;

    public RoleStateMemento getMemento() {
        return memento;
    }

    public void setMemento(RoleStateMemento memento) {
        this.memento = memento;
    }
}
