/*
    - 중요도가 높은 문서를 먼저 인쇄하는 프린터
    - 인쇄 대기목록의 가장 앞에 있는 문서 J를 대기목록에서 꺼낸다
    - 나머지 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 있으면 J를 대기목록의 가장 마지막에 넣는다
    - 그렇지 않으면 J를 인쇄
*/

import java.util.*;

class Solution {

    public int solution(int[] priorities, int location) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;
        
        for (int i = 0; i < priorities.length; i++) {
            pq.add(priorities[i]);
        }
        
        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorities[i] == pq.peek()) { // 제일 우선순위가 큰 값을 조회
                    if (i == location) { // location 과 같으면 정답
                        answer++;
                        return answer;
                    }
                    pq.poll();
                    answer++;
                }
            }
        }
        return -1;
    }
}
