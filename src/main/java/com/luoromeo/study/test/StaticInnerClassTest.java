package com.luoromeo.study.test;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月27日 16:39
 * @modified By
 */
public class StaticInnerClassTest {

    public static void main(String[] args) {

    }

}

class ArrayAlg {
    public static class Pair {
        private double first;

        private double second;

        public Pair  (double f, double s) {
            first = f;
            second = s;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }

    public static Pair minmax(double[] value) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (double v : value) {
            if (min > v) {
                min = v;
            }
            if (max < v) {
                max = v;
            }
        }

        return new Pair(max, max);
    }
}