package com.luoromeo.study.gof.ps;

public class Test {

    public static void main(String[] args) {

        //前台小姐童子喆
        Secretary tongzizhe = new Secretary();

        //看股票的同事
        StockObserver tongshi1 = new StockObserver("同事1", tongzizhe);
        NbaObserver tongshi2 = new NbaObserver("同事2", tongzizhe);

        //前台记下两位同事
        tongzizhe.attach(tongshi1);
        tongzizhe.attach(tongshi2);

        tongzizhe.setAction("老板回来了");

        tongzizhe.notifyy();


    }
}
