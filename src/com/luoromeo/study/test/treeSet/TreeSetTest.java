package com.luoromeo.study.test.treeSet;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月31日 16:36
 * @modified By
 */
public class TreeSetTest {
    public static void main(String[] args) {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));

        System.out.println("Iterating over elemetns...");
        for (LocalDate localDate : pq) {
            System.out.println(localDate);
        }

        System.out.println("Removing elements...");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
            System.out.println(pq.remove());
        }


    }
}
