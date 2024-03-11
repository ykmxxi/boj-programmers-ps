/*
    - 모든 차량이 한 번은 카메라를 만나도록 카메라 설치
    - 차량의 경로가 주어질 때 모든 차량이 한 번은 카메라를 만나도록 하려면 최소 몇 대의 카메라를 설치해야 하는가
*/

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]); // 끝 구간이 짧은 순서대로 정렬
        
        int prev = routes[0][1]; // 첫 번쨰 설치 지점: 첫 자동차의 끝 구간
        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] <= prev) { // 이전에 설치된 카메라의 끝구간보다 현재 경로의 시작 지점이 작으면 설치 필요 X
                continue;
            }
            prev = routes[i][1]; // 새로운 카메라 설치 후 이전 카메라 끝 갱신
            answer++;
        }
        return answer;
    }
}
