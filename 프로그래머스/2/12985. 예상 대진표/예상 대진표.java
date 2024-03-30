/*  
    - 게임대회는 N명이 참가, 토너먼트 형식으로 진행, 각 참가자는 차례대로 1번 ~ N번
    - 1번:2번, 3번:4번, ..., N-1번:N번 참가자끼리 게임을 진행, 이긴 사람은 다음 라운드 진출
    - 다음 라운드 참가자는 1번부터 N/2번을 다시 차례대로 배정받음
    - 다시 1번:2번, ..., N/2 - 1번:N/2번 게임 진행
    - 최종 한 명이 남을 때까지 진행
    - 처음 라운드에서 a번을 가진 참가자는 b번 참가자와 몇 번째 라운드에서 만나는지 return
    
    2^1 <= N <= 2^20, 2^20은 약 10^6(int), 2의 지주 승으로 주어져 부전승은 없음
*/


class Solution {
    
    static int getNextNumber(int num) {
        if (num % 2 == 0) {
            return num / 2;
        }
        
        return num / 2 + 1;
    }
    
    public int solution(int n, int a, int b) {
        int answer = 1;
        
        while (Math.abs(a - b) != 1 || Math.max(a, b) % 2 != 0) { // 2, 3은 안됨 -> 가장 큰 참가자의 번호가 짝수여야 함
            a = getNextNumber(a);
            b = getNextNumber(b);
            answer++;
        }

        return answer;
    }
}