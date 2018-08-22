package com.luoromeo.study.gof.visitor;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月22日 18:16
 * @modified By
 */
public class Test {
    public static void main(String[] args) {
        ObjectStructure structure = new ObjectStructure();
        structure.attach(new Man());
        structure.attach(new Woman());

        structure.display(new Success());
    }
}
