package com.luoromeo.study.gof.ps;

/**
 * 看股票同事类
 */
public class StockObserver extends Observer {


    public StockObserver(String name, Secretary sub) {
        super(name, sub);
    }

    public void update() {
        System.out.printf("%s%s 关闭股票行情，继续工作！\n", sub.getAction(), name);
    }
}
