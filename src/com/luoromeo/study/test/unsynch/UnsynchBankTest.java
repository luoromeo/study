package com.luoromeo.study.test.unsynch;

import java.text.SimpleDateFormat;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月02日 15:21
 * @modified By
 */
public class UnsynchBankTest {

    public static final ThreadLocal<SimpleDateFormat> dateFormater = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static final int NACCOUNTS = 10;

    public static final double INITIAL_BALANCE = 1000;

    public static final double MAX_ACCOUNT = 100;

    public static final int DELAY = 10;

    public static void main(String[] args) {

        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_ACCOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((long) (DELAY * Math.random()));
                    }
                } catch (InterruptedException ignore) {

                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}