/*
    - 큰 뒷수: 배열의 각 원소들에 대해 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수
    - 완탐: 최악의 경우 O(10^12), 100초가 걸림
*/

import java.util.*;

class Solution {
    
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Deque<Integer> dq = new ArrayDeque<>();
        dq.push(0); // 0 번 인덱스 먼저 넣기
        
        Arrays.fill(answer, -1);
        
        for (int i = 1; i < numbers.length; i++) {
            while (!dq.isEmpty() && numbers[dq.peek()] < numbers[i]) {
                answer[dq.pop()] = numbers[i];
            }
            dq.push(i);
        }
        
        
        return answer;
    }
    
}
