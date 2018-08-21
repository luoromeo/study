package com.luoromeo.study.test.contended;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月19日 09:57
 * @modified By
 */
public class FalseSharing implements Runnable {

    public final static long ITERATIONS = 500L * 1000L * 1000L;

    private static VolatileLong volatileLong;

    private String groupId;

    public FalseSharing(String groupId) {
        this.groupId = groupId;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("starting....");

        volatileLong = new VolatileLong();
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread t0 = new Thread(new FalseSharing("t0"));
        Thread t1 = new Thread(new FalseSharing("t1"));
        t0.start();
        t1.start();
        t0.join();
        t1.join();
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        if ("t0".equals(groupId)) {
            while (0 != --i) {
                volatileLong.value1 = i;
//                volatileLong.value3 = i;
            }
        } else if ("t1".equals(groupId)) {
            while (0 != --i) {
                volatileLong.value1 = i;
//                volatileLong.value4 = i;
            }
        }
    }
}
