package com.luoromeo.study.test.concurrent.sample.logger;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月25日 16:40
 * @modified By
 */
public class LogService {

    private final BlockingQueue<String> queue;

    private final LoggerThread logger;

    private final PrintWriter writer;

    public LogService(PrintWriter writer) {
        this.writer = writer;
        queue = new LinkedBlockingDeque<>(1024);
        logger = new LoggerThread();
    }

    private boolean isShutdown;

    private int reservations;

    public void start() {
        logger.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        logger.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException("isShutdown");
            }
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.this) {
                            if (isShutdown && reservations == 0) {
                                break;
                            }
                        }
                        String msg = queue.take();
                        synchronized (LogService.this) {
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {
                        /**
                         * retry
                         */
                    } finally {
                        writer.close();
                    }
                }
            } finally {
                writer.close();
            }


        }
    }

}
