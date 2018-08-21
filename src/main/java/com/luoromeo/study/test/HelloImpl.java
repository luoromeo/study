package com.luoromeo.study.test;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月04日 11:40
 * @modified By
 */
public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Hello! " + name);
    }
}
