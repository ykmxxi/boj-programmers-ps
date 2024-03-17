/*
    - 길이가 같은 두 개의 큐가 존재, 하나의 큐에서 원소를 추출하고 다른 큐에 집어넣어 각 큐의 합이 같도록 만들기
    - 이때 필요한 작업의 최소 횟수
*/

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long total = 0L;
        long sumOne = 0L;
        int max = 0;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            total += queue1[i] + queue2[i];
            sumOne += queue1[i];
            
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
            
            max = Math.max(max, Math.max(queue1[i], queue2[i]));
        }
        
        if (max > total / 2 || total % 2 == 1) { // 최대값이 총 합의 절반보다 크거나 또는 총 합이 홀수이면 못만듬
            return -1;
        }
        
        long target = total / 2;
        int cnt = 0;
        
        while (cnt <= queue1.length * 4) {
            if (sumOne > target) { // q1이 더 크면
                sumOne -= q1.peek();
                q2.offer(q1.poll());
            } else if (sumOne < target) {
                sumOne += q2.peek();
                q1.offer(q2.poll());
            } else {
                break;
            }
            cnt++;
        }
        
        return cnt > queue1.length * 4 ? -1 : cnt;
    }
}
