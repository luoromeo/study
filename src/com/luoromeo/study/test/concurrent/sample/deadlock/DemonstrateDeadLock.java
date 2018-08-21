package com.luoromeo.study.test.concurrent.sample.deadlock;

import java.util.Arrays;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月28日 15:11
 * @modified By
 */
public class DemonstrateDeadLock {

    private static final int NUM_THREADS = 250;

    private static final int NUM_ACCOUNT = 5;

    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        // final Random rnd = new Random();
        // final Account[] accounts = new Account[NUM_ACCOUNT];
        //
        // for (int i = 0; i < accounts.length; i++) {
        // accounts[i] = new Account();
        // }
        // class TransferThread extends Thread {
        // @Override
        // public void run() {
        // for (int i = 0; i < NUM_ITERATIONS; i++) {
        // int fromAcct = rnd.nextInt(NUM_ACCOUNT);
        // int toAcct = rnd.nextInt(NUM_ACCOUNT);
        //
        // }
        // }
        // }

        int[] a = new int[] { 3, 2, 1, 5, 6, 7, 2, 5 };
        maopao(a);
    }

    static void maopao(int[] a) {
        int len = a.length;
        int min = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = len - 1; j > i; j--) {
//                if (a[i] > a[j]) {
//                    min = a[j];
//                    a[j] = a[i];
//                    a[i] = min;
//                }
                if (a[i] < a[j]) {
                    min = a[i];
                    a[i] = a[j];
                    a[j] = min;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
