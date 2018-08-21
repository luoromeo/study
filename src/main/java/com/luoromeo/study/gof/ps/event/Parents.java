package com.luoromeo.study.gof.ps.event;

public class Parents {

    public void phoneNotice(Student s) {
        System.out.println("通知家长:" + s.getName() + "考试分数 == " + s.getScore());
    }
}
