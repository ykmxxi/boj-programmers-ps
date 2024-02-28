/*
    - 요청 시간대로 오름차순 정렬해서 처리
    - 작업 소요 시간대로 오름차순 정렬해서 처리
*/

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int sum = 0;
        
        Arrays.sort(jobs, (o1, o2) -> (o1[0] - o2[0])); // 요청 시간 오름차순 정렬

        // 요청시점, 소요시간
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1])); // 작업시간 오름차순 정렬

        int sec = 0; // 시간
        int idx = 0; // 배열 인덱스
        int cnt = 0; // 요청 수행 갯수

        while(cnt < jobs.length) {
            while(idx < jobs.length && jobs[idx][0] <= sec)
                q.add(jobs[idx++]);

            if(q.isEmpty()) {
                sec = jobs[idx][0];
            } else {
                int[] tmp = q.poll();
                sum += tmp[1] + sec - tmp[0];
                sec += tmp[1];
                cnt++;
            }
        }
    
        return sum / jobs.length;
    }

}