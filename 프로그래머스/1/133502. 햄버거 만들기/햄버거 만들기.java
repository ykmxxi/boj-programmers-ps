/*
    - 조리된 순서대로 앞에 아래서부터 위로 쌓임(stack)
    - 빵-야채-고기-빵 포장
    - 1: 빵, 2: 야채, 3:고기 (1-2-3-1)
*/

import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        
        for (int a : ingredient) {
            dq.push(a);
            
            if (dq.size() >= 4 && dq.peek() == 1) {
                Deque<Integer> tmp = new ArrayDeque<>();
                tmp.push(dq.pop());
                
                for (int i = 3; i >= 1; i--) {
                    if (dq.peek() == i) {
                        tmp.push(dq.pop());
                    }
                }
                
                if (tmp.size() == 4) {
                    answer++;
                } else {
                    while (!tmp.isEmpty()) {
                        dq.push(tmp.pop());
                    }
                }
            }
        }
        
        return answer;
    }
}