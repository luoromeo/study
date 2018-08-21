package com.luoromeo.study.test.concurrent.sample;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月26日 11:14
 * @modified By
 */
public class Test {
        public static int peakIndexInMountainArray(int[] A) {
            int len = A.length;
            int left = 0;
            int right = len - 1;
            int mid = -1;
            while (left < right) {
                mid = left + (right - left) / 2;
                if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                    return mid;
                } else if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1]) {
                    left = mid;
                } else if (A[mid] < A[mid - 1] && A[mid] > A[mid + 1]) {
                    right = mid;
                }

            }
            return mid;
    }

    public static void main(String[] args) {
        int[] A = {0,2,1,0};
        System.out.println(peakIndexInMountainArray(A));
    }
}
