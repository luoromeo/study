package com.luoromeo.study.gof.ps.event;

public class Student {
    private String name;

    //分数
    private double score;

    public Student(String name, double score) {
        super();
        this.name = name;
        this.score = score;
    }

    public void speak() {
        System.out.println(name + "考试分数 == " + score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


}