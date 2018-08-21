package com.luoromeo.study.test;

import sun.jvm.hotspot.StackTrace;

import java.util.Scanner;
import java.util.function.IntFunction;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月30日 14:35
 * @modified By
 */
public class StackTraceTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = -1;
        System.out.print("Enter n: ");
        while ((n = in.nextInt()) != 0) {
            factorial(n);
            System.out.print("Enter n: ");
        }
    }

    private static int factorial(int n) {
        System.out.println("factorial(" + n + "):");
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement frame : frames) {
            System.out.println(frame);
        }

        int r ;
        if (n <= 1) {
            r = 1;
        } else {
            r = n * factorial(n - 1);
        }

        System.out.println("return " + r);
        return r;
    }

}
