/*
    - 두 조각으로 나누어 각 조각에 동일한 가짓수의 토핑이 올라가면 공평하게 나누어진 것
    - 롤케이크를 공평하게 자르는 방법의 수 구하기
    - 완탐 -> O(n^2) -> 10^12 -> 약 100초(시간 초과)
*/

import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        for (int t : topping) {
            set.add(t);
        }
        int[] cnt = new int[10001];
        for (int t : topping) {
            cnt[t]++;
        }
        
        if (topping.length == 1) {
            return 0;
        }
        
        boolean[] visit = new boolean[10001];
        visit[topping[0]] = true;
        cnt[topping[0]] -= 1;
        
        int leftCnt = 1;
        int rightCnt = set.size();
        if (cnt[topping[0]] == 0) {
            rightCnt = set.size() - 1;
        }
        if (leftCnt == rightCnt) {
            answer++;
        }
        
        for (int L = 1; L < topping.length - 1; L++) {
            cnt[topping[L]] -= 1;
            if (!visit[topping[L]]) { // 왼쪽에 처음 추가된 토핑이면
                leftCnt += 1;
                visit[topping[L]] = true;
            }
            
            if (cnt[topping[L]] == 0) {
                rightCnt -= 1;
            }
            
            if (leftCnt == rightCnt) {
                answer++;
            }
        }
        
        return answer;
    }
}