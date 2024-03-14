/*
    - 택배상자의 크기는 모두 같고 1 ~ n번 순서대로 전달됨
    - 전달은 한 방향으로만 진행이 가능해 놓인 순서대로 내릴 수 있음
    - 보조 벨트는 스택 구조(LIFO)
    - 보조 벨트를 이용해도 원하는 순서대로 상자를 쌓지 못하면 더 이상 싣지 않음
*/

import java.util.*;

class Solution {
    
    public int solution(int[] order) {
        int answer = 0;
        
        Queue<Integer> q = new LinkedList<>();
        Deque<Integer> dq = new ArrayDeque<>(); // 보조
        
        for (int i = 1; i <= order.length; i++) {
            q.offer(i);
        }
        
        for (int o : order) {
            boolean find = false;
            if (!q.isEmpty() && q.peek() == o) { // 기존 벨트의 첫 번째에 원하는 상자가 있으면
                q.poll();
                answer++;
                continue;
            } 
            
            if (!dq.isEmpty() && dq.peek() == o) { // 기존 벨트에 없으면 보조 벨트 먼저 확인
                dq.pop();
                answer++;
                continue;
            }
                
            while (!q.isEmpty()) { // 보조 벨트에도 없으면 보조 벨트로 옮기기
                int move = q.poll();
                if (move == o) {
                    find = true;
                    answer++;
                    break;
                }
                dq.push(move);
            }
            
            if (!find) { // 보조 벨트로 옮기며 못찾았으며 더 이상 싣지 못한다
                break;
            }
        }
        
        
        return answer;
    }
    
}
