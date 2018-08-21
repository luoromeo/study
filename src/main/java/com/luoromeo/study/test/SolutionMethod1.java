package com.luoromeo.study.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @description
 *
 *              题目描述：给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 *              那么一共存在6个滑动窗口，他们的最大值分别为{4.4,6,6,6,5}。 输入描述：请输入一个数组：以空格隔开 2 3 4 2
 *              6 2 5 1 请输入滑动窗口的大小： 3 程序输出： 滑动窗口的最大值为： [4, 4, 6, 6, 6, 5] 问题分析：
 *              队列：队列中允许进行插入操作的一端称为队尾，允许进行删除操作的一端称为队头。队列的插入操作通常称作入队列，队列的删除操作通常称作出队列。
 *              算法描述：见程序
 * @author zhanghua.luo
 * @date 2018年05月25日 11:23
 * @modified By
 */
public class SolutionMethod1 {

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (num == null) {
            return ret;
        }
        if (num.length < size || size < 1) {
            return ret;
        }
        // 用于保存滑动窗口中的数字
        LinkedList<Integer> indexDeque = new LinkedList<>();
        // 滑动窗口内部，用于判断窗口中的最大值
        for (int i = 0; i < size - 1; i++) {
            // getLast为插入端
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                // 将前面比K小的直接移除队列，因为不可能成为滑动窗口的最大值
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
        }

        // 滑动整个窗口
        for (int i = size - 1; i < num.length; i++) {
            // getLast为插入端，队尾
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                // 将前面比K小的直接移除队列，因为不可能成为滑动窗口的最大值
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
            // System.out.println("indexDeque = " + indexDeque.getFirst() + ",i = " + i);
            // //getFirst为允许删除端，队头
            if (i - indexDeque.getFirst() + 1 > size) {
                indexDeque.removeFirst();
            }
            // 每次添加的是num[indexDeque.getFirst()],而不是indexDeque.getFirst().
            ret.add(num[indexDeque.getFirst()]);
        }
        return ret;
    }
}

class MaxInWindows {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个数组：以空格隔开");
        String str = scanner.nextLine();

        System.out.println("请输入滑动窗口的大小：");
        int k = scanner.nextInt();

        String[] tmp = str.split(" ");
        int[] arrays = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            arrays[i] = Integer.parseInt(tmp[i]);
        }
        scanner.close();

        SolutionMethod1 solution1 = new SolutionMethod1();
        System.out.println("滑动窗口的最大值为：");
        System.out.println(solution1.maxInWindows(arrays, k));
    }
}