/*
    - 영어 단어가 적힌 카드 뭉치 두 개, 규칙에 맞게 원하는 순서의 단어 배열을 만들려고 함
    - 뭉치에서 카드를 순서대로 한 장씩 사용, 한 번 사용한 카드는 다시 사용 X
    - 사용하지 않고 다음 카드로 넘어갈 수는 없음
    - 주어진 카드 뭉치의 순서는 바꿀 수 없음
*/

import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        
        Deque<String> dq1 = new ArrayDeque<>();
        Deque<String> dq2 = new ArrayDeque<>();
        Deque<String> dq3 = new ArrayDeque<>();
        
        for (String s : cards1) {
            dq1.offer(s);
        }
        
        for (String s : cards2) {
            dq2.offer(s);
        }
        
        for (String s : goal) {
            dq3.offer(s);
        }
        
        int order = -1;
        if (dq1.peek().equals(dq3.peek())) { // cards1 에서 부터 뽑아야 하면
            order = 0;
        } else if (dq2.peek().equals(dq3.peek())) { // cards2 에서 부터 뽑아야 하면
            order = 1;
        }
        
        if (order == -1) {
            return "No";
        }
        
        while (true) {
            if (order % 2 == 0) { // 짝수면 cards1
                if (!dq1.isEmpty() && dq1.peek().equals(dq3.peek())) {
                    order++;
                    dq1.poll();
                    dq3.poll();
                    
                    while (!dq1.isEmpty() && dq1.peek().equals(dq3.peek())) {
                        dq1.poll();
                        dq3.poll();
                    }
                    continue;
                }
            } else { // 홀수면 cards2
                if (!dq2.isEmpty() && dq2.peek().equals(dq3.peek())) {
                    order++;
                    dq2.poll();
                    dq3.poll();
                    
                    while (!dq2.isEmpty() && dq2.peek().equals(dq3.peek())) {
                        dq2.poll();
                        dq3.poll();
                    }
                    continue;
                }
            }
            break;
        }
        
        if (dq3.isEmpty()) {
            return "Yes";
        }
        return "No";
    }
}