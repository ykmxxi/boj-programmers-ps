/*
    - 2*N명 사원들이 N명씩 두 팀으로 나눠 숫자 게임을 한다
    규칙 1. 먼저 모든 사원이 무작위로 자연수를 하나씩 받음
    규칙 2. 각 사원은 딱 한 번씩 경기
    규칙 3. 각 경기탕 A팀 1명, B팀 1명이 나와 서로의 수를 공개하고 큰 쪽이 승리하면 승점 1 얻음
    규칙 4. 숫자가 같으면 아무도 승점 X
    - A팀이 출전순서를 공개해 B팀이 승점을 가장 높이는 방법을 채택했을 때 얻는 승점의 최대값 구하기
*/

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        boolean[] visit = new boolean[B.length];
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[p1] >= B[p2]) {
                p2++;
            } else {
                p1++;
                p2++;
                answer++;
            }
        }
        
        return answer;
    }
}