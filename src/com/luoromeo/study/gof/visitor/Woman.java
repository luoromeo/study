package com.luoromeo.study.gof.visitor;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 18:08
 * @modified By
 */
public class Woman implements Person {
    @Override
    public void accept(Action action) {
        action.getWomanConclusion(this);
    }
}
