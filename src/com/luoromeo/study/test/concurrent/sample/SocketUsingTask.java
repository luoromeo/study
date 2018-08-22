package com.luoromeo.study.test.concurrent.sample;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月25日 14:12
 * @modified By
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {

    private Socket socket;

    private synchronized void setSocket(Socket s) {
        this.socket = s;
    }

    @Override
    public void cancel() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ignore) {

        }
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}
