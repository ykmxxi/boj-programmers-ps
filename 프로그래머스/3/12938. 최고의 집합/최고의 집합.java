/*
    - 자연수 n개로 이루어진 중복 집합 중 두 조건을 만족하는 집합을 최고의 집합
    1. 각 원소의 합이 S
    2. 1번을 만족하면서 각 원소의 곱이 최대가 되는 집합
    
*/

import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer;
        
        if (n > s) { // 모든 수의 합이 n보다 작으면 존재 X
            return new int[]{-1};
        }
        if (n == s) {
            answer = new int[n];
            for (int i = 0; i < n; i++) {
                answer[i] = 1;
            }
            return answer;
        }
        
        int val = s / n;
        int remain = s % n;
        
        answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = val;
            if (remain > 0) {
                answer[i]++;
                remain--;
            }
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}