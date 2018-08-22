package com.luoromeo.study.gof.visitor;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 18:06
 * @modified By
 */
public interface Person {

    void accept(Action action);
}
