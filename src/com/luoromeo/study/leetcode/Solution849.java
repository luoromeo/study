package com.luoromeo.study.leetcode;

/**
 * @description 849. 到最近的人的最大距离
 * 在一排座位（ seats）中，1 代表有人坐在座位上，0 代表座位上是空的。
 * 至少有一个空座位，且至少有一人坐在座位上。
 * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
 * 返回他到离他最近的人的最大距离。
 *
 * 示例1:
 * 输入：[1,0,0,0,1,0,1]
 * 输出：2
 * 解释：
 * 如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
 * 如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
 * 因此，他到离他最近的人的最大距离是 2 。
 *
 * 示例2:
 * 输入：[1,0,0,0]
 * 输出：3
 * 解释：
 * 如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。
 * 这是可能的最大距离，所以答案是 3 。
 * @author zhanghua.luo
 * @date 2018年07月03日 09:43
 * @modified By
 */
public class Solution849 {
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 0, 1, 0, 0, 0, 0 };
        maxDistToClosest(arr);
    }

    public static int maxDistToClosest(int[] seats) {
        //第一个被占的位置
        int start = 0;
        //最后一个被占的位置
        int end = seats.length - 1;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] != 0) {
                start = i;
                break;
            }
        }

        for (int i = seats.length - 1; i >= 0; i--) {
            if (seats[i] != 0) {
                end = i;
                break;
            }
        }
        int a = max(start, end, seats);
        //考虑end到数组最后一个的距离
        int b = Math.max(start, seats.length - 1 - end);
        return Math.max(b, a);
    }

    public static int max(int a, int b, int[] arr) {

        int count = 0;
        int max = 0;
        int res = 0;

        //循环获得start end之间最大的距离
        for (int i = a + 1; i < b; i++) {
            if (arr[i] == 0) {
                count++;
                if (count > max) {
                    max = count;
                }
            } else {
                count = 0;
            }
        }
        //坐中间
        if (max % 2 != 0) {
            res = (max + 1) / 2;
        } else {
            res = max / 2;
        }
        return res;
    }

}
