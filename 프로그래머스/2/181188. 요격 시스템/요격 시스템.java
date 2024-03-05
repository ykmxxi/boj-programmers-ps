/*
    - A 나라가 발사한 폭격 미사일: x 축에 평행한 직선 형태의 모양
    - B 나라의 요격 시스템: 특정 x좌표에서 y축에 수평이 되도록, x좌표에 있는 모든 미사일을 요격
    - [s, e]로 주어질 때 s or e 에서 요격할 수 없음
*/

import java.util.*;

class Solution {
    
    
    public int solution(int[][] targets) {
        int answer = 0; // 최악의 경우 500,000개 필요
        
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[1] == o2[1]) { // 끝 구간이 같으면
                return o1[0] - o2[0]; // 시작 구간이 작은것 부터
            }
            return o1[1] - o2[1]; // 끝 구간이 작은것 부터
        });
        
        int end = targets[0][1];
        answer++; // 첫 번째로 만들어지는 요격 시스템
        
        for (int[] target : targets) {
            if (target[0] >= end) { // 다음 미사일의 시작 구간이 이전 요격 시스템의 끝 구간보다 크면
                end = target[1];
                answer++; // 시점이 요격 시스템의 상한보다 오른쪽에 있기 때문에 새로운 요격 시스템 추가
                System.out.println(end);
            }
        }
        
        return answer;
    }
    
}