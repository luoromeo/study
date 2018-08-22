package com.luoromeo.study.test.sieve;

import java.util.BitSet;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月02日 09:45
 * @modified By
 */
public class Sieve {
    public static void main(String[] args) {
        int n = 2000000;

        long start = System.currentTimeMillis();

        BitSet b = new BitSet(n + 1);
        int count = 0;
        int i;
        for (i = 2; i<=n; i++) {
            b.set(i);
        }
        i = 2;
        while (i * i <= n) {
            if (b.get(i)) {
                count ++;
                int k = i << 1;
                while (k <= n) {
                    b.clear(k);
                    k += i;
                }
            }
            i ++;
        }

        while (i <= n) {
            if (b.get(i)) {
                count ++;
            }
            i ++;
        }
        long end = System.currentTimeMillis();
        System.out.println(count + " primes ");
        System.out.println(end - start);
    }
}
