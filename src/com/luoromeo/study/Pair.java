package com.luoromeo.study;

import com.luoromeo.study.test.A;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月31日 09:53
 * @modified By
 */
public class Pair<T extends A>  {

    private T first;

    private T second;

    public Pair  (T f, T s) {
        first = f;
        second = s;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
