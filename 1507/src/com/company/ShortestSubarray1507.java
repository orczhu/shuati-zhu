package com.company;

public class ShortestSubarray1507 {
    /**
     * @param A: the array
     * @param K: sum
     * @return: the length
     * https://www.lintcode.com/problem/1507/
     */
    public int shortestSubarray(int[] A, int k) {
         if (A == null || A.length == 0) {
             return -1;
         }
         int[] prefixSum = new int[A.length + 1];
         prefixSum = getSum(A);
         int start = 0;
         int end = prefixSum.length - 1;
         while (start + 1  < end) {
             int mid = start + (end - start) / 2;
             if (isValid(prefixSum, k, mid)) {
                 end = mid;
             } else {
                 start = mid;
             }
         }

         if (isValid(prefixSum, k, start)) {
             return start;
         }
         if (isValid(prefixSum, k, end)) {
             return end;
         }

         return -1;
    }

    private boolean isValid(int[] prefixSum, int k, int length) {
        // enumerate end to try if I can find the one which can >= k
        Heap heap = new Heap();
        for (int end = 0; end < prefixSum.length; end++) {
             int startIdx = end - length - 1;
             heap.delete(startIdx);
             if (!heap.isEmpty() && prefixSum[end] - heap.top().val >= k) {
                 return true;
             }
             heap.push(end, prefixSum[end]);
         }

        return false;
    }

    private int[] getSum(int[] A) {
        int[] prefixSum = new int[A.length + 1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i - 1];
        }
        return prefixSum;
    }
}
