package com.luoromeo.study.gof.visitor;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 18:07
 * @modified By
 */
public class Success implements Action {


    @Override
    public void getManConclusion(Man man) {
        System.out.println("男人成功时，背后多半有一个伟大的女人");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println("女人成功时，背后多半有一个伟大的男人");
    }
}
