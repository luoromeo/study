package com.luoromeo.study.test.concurrent.sample.logger;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @description 不支持关闭的生产者-消费者日志服务
 * @author zhanghua.luo
 * @date 2018年06月25日 16:25
 * @modified By
 */
public class LogWriter {

    private final BlockingQueue<String> queue;

    private final LoggerThread logger;

    public LogWriter(PrintWriter writer) {
        this.queue = new LinkedBlockingDeque<>(1024);
        this.logger = new LoggerThread(writer);
    }

    public void start() {
        logger.start();
    }

    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }

    private class LoggerThread extends Thread {

        private PrintWriter writer;

        public LoggerThread(PrintWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    writer.println(queue.take());
                }
            } catch (InterruptedException ignore) {

            } finally {
                writer.close();
            }
        }
    }
}
