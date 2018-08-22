package com.luoromeo.study.gof.visitor;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 18:07
 * @modified By
 */
public class Man implements Person {
    @Override
    public void accept(Action action) {
        action.getManConclusion(this);
    }
}
