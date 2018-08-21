package com.luoromeo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月03日 15:30
 * @modified By
 */
public class Test {

    public static void main(String[] args) {

        Map<Integer, Integer> a = new HashMap<>();

        long start = System.currentTimeMillis();
        for (Integer i = 0; i < 100000; i++) {
            a.put(i, i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        Assert.assertTrue(!a.isEmpty());

    }
}
