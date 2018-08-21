package com.luoromeo.study.test.concurrent.sample;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @description 自定义Thread示例，可以配合ThreadFactor使用
 * @author zhanghua.luo
 * @date 2018年06月27日 16:55
 * @modified By
 */
public class MyAppThread extends Thread {

    public static final String DEFAULT_NAME = "my-app-thread";

    private static volatile boolean debugLifecycle = false;

    private static final AtomicInteger created = new AtomicInteger();

    private static final AtomicInteger alive = new AtomicInteger();

    private static final Logger log = Logger.getAnonymousLogger();

    public MyAppThread(Runnable r) {
        this(r, DEFAULT_NAME);
    }

    public MyAppThread(Runnable r, String name) {
        super(r, name + created.incrementAndGet());

        setUncaughtExceptionHandler((t, e) -> log.log(Level.SEVERE, "UNCAUGHT in thread" + t.getName(), e));
    }

    @Override
    public void run() {
        boolean debug = debugLifecycle;

        if(debug) {
            log.log(Level.FINE, "Created" + getName());
        }

        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                log.log(Level.FINE, "Exiting " + getName());
            }
        }

    }

    public static int getThreadCreated() {
        return created.get();
    }

    public static int getThreadAlive() {
        return alive.get();
    }

    public static boolean getDebug() {
        return debugLifecycle;
    }

    public static void setDebug(boolean b) {
        debugLifecycle = b;
    }
}
