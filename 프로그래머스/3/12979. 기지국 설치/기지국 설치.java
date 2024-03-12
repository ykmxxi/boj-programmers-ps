/*
    - n개의 아파트가 일렬로 있고, 일부 위치에 4g 설치되어 있음
    - 5g를 설치하려고 하는데 5g는 4g보다 짧음
    - 모든 아파트에 전파가 전달하기 위해 증설해야 할 기지국 개수의 최소값 구하기
*/

import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int now = 1; // 현재 위치
        int stationIdx = 0; // 기지국 idx
        int answer = 0; // 설치해야하는 기지국 개수

        // 현재 위치가 범위 이내인 경우 순회
        while (now <= n) {
            
            // 현재 위치가 모든 기지국의 범위를 넘어선 경우 || 현재 위치가 기지국의 범위보다 작은 경우
            if (stationIdx >= stations.length || now < stations[stationIdx] - w) {
                // now + w 설치한다고 가정
                answer++;
                
                // now 를 새로 설치한 기지국의 범위 밖으로 이동
                now = now + (2 * w + 1);
            } else { // 현재위치가 모든 기지국의 범위보다 작으며 특정 기지국에 범위내에 있는 경우
                
                // 현재 위치를 해당 기지국 밖으로 이동
                now = stations[stationIdx] + w + 1;
                
                // 계산할 기지국을 다음 기지국으로 변경
                stationIdx++;
            }
        }
        
        return answer;
    }
}