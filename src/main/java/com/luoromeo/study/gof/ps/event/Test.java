package com.luoromeo.study.gof.ps.event;

public class Test {

    public static void main(String[] args) {
        Teacher teacher = new Teacher("老老师");

        Student liq = new Student("李青", 28.1);

        Student dem = new Student("德玛", 66.1);

        Parents parents = new Parents();

        teacher.addObserver("speak", liq, null);
        teacher.addObserver("speak", dem, null);
        teacher.addObserver("phoneNotice", parents, new Object[]{liq});

        teacher.notifyObserver();

    }
}
