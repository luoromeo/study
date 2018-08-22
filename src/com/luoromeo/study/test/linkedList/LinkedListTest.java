package com.luoromeo.study.test.linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月31日 15:32
 * @modified By
 */
public class LinkedListTest {

    public static void main(String[] args) {
        List<String> a = new LinkedList<>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        List<String> b = new LinkedList<>();
        b.add("bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        //merge the words from b into a
        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext()) {
                aIter.next();
            }
            aIter.add(bIter.next());
        }

        System.out.println(a);

        //remove every second word form b
        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next(); //skip one element
            if (bIter.hasNext()) {
                bIter.next(); // skip this element
                bIter.remove(); // remove this element
            }
        }
        System.out.println(b);

        a.removeAll(b);

        System.out.println(a);
    }
}
