package com.luoromeo.study.gof.ps;

public class NbaObserver extends Observer {
    public NbaObserver(String name, Secretary sub) {
        super(name, sub);
    }

    @Override
    public void update() {
        System.out.printf("%s%s 关闭NBA直播，继续工作！\n", sub.getAction(), name);
    }
}
