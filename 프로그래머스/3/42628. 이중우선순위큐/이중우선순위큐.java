/*
    - "l 숫자": 큐에 숫자 삽입
    - "D 1": 최대값 삭제, "D -1": 최소값 삭제
    
*/

import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 오름차순
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
        
        for (String opr : operations) {
            StringTokenizer st = new StringTokenizer(opr, " ");
            
            String s1 = st.nextToken();
            int n = Integer.parseInt(st.nextToken());
            
            if ("I".equals(s1)) { // 삽입
                pq.offer(n);
                maxPq.offer(n);
            } else { // 삭제 연산
                if (pq.isEmpty()) { // 비어있으면 무시
                    continue;
                }
                if (n == 1) { // 최대값 삭제
                    int max = maxPq.poll();
                    pq.remove(max);
                } else { // 최소값 삭제
                    int min = pq.poll();
                    maxPq.remove(min);
                }
            }
        }
        
        int[] ans = new int[2];
        if (pq.size() >= 1) {
            ans[0] = maxPq.poll();
            ans[1] = pq.poll();
        } else {
            ans[0] = 0;
            ans[1] = 0;
        }
        return ans;
        
    }
}