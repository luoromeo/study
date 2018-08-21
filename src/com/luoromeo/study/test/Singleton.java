package com.luoromeo.study.test;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月05日 09:45
 * @modified By
 */
public class Singleton {
    private static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    public void ha() {
        System.out.println("hahahhahahaha");
    }
}
