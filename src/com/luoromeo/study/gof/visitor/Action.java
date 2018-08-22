package com.luoromeo.study.gof.visitor;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 18:05
 * @modified By
 */
public interface Action {

    void getManConclusion(Man man);

    void getWomanConclusion(Woman woman);
}
