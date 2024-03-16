/*
    - 징검다리는 일렬, 각 징검다리의 디딤돌에 숫자가 있으며 한 번 밟을 때마다 1씩 줄어듦
    - 디딤돌의 숫자가 0이 되면 더 이상 밟을 수 없으며 그 다음 디딤돌로 한번에 여러 칸 건너 뛸 수 있다
    - 다음으로 밟을 수 있는 디딤돌이 여러 개인 경우 무조건 가까운 디딤돌로 뛰어야 함
    - 한 사람이 모두 건넌 후 그 다음 사람이 건너기 시작
    - 최대 몇 명까지 징검다리를 건널 수 있나?(이분탐색 or 매개변수 탐색)
*/

import java.util.*;

class Solution {
    
    boolean can(int mid, int[] stones, int k) {
        int cnt = 0;
        
        for (int s : stones) {
            if (s < mid) { // 해당 징검다리를 mid명이 건널 수 없으면
                cnt++;
            } else { // 해당 징검다리를 mid명이 건널 수 있으면 건너 뛰는 디딤돌의 개수를 0으로 초기화
                cnt = 0;
            }
            
            if (cnt == k) {
                return false;
            }
        }
        
        return true;
        
    }
    
    public int solution(int[] stones, int k) {
        int answer = 1;
        int L = 1;
        int R = 200_000_000; // 최대 2억명이 건널 수 있음
        
        while (L <= R) {
            int mid = (L + R) / 2;
            
            if (can(mid, stones, k)) { // mid명이 건널 수 있으면 구간 시작 땡기기
                answer = mid;
                L = mid + 1;
            } else { // mid명이 건널 수 없으면 구간 끝 땡기기
                R = mid - 1;
            }
        }
        
        return answer;
    }
    
}
