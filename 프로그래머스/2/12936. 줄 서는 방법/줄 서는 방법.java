/*
    - n명의 사람을 나열 -> 중복없는 순열
    - k번째 방법을 찾기
*/

import java.util.*;

class Solution {
    
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        
        long[] cnt = new long[21];
        List<Integer> numbers = new ArrayList<>();
        cnt[0] = 1L;
        
        for (int i = 1; i <= 20; i++) {
            cnt[i] = cnt[i - 1] * i;
            numbers.add(i);
        }
        
        int idx = 0;
        k--;
        
        while (n > 0) {
            // 각 자리에 자리에 들어갈 경우의 수
            long section = cnt[n] / n;
            
            // 자리 숫자 결정
            int val = (int) (k / section);
            
            // 정답 배열에 숫자 삽입
            answer[idx] = numbers.get(val);
            numbers.remove(val);
            
            // 다음 자리수를 구하기 위해 k값 변경
            k %= section;
            idx++;
            n--;
        }
        
        return answer;
    }
    
}
