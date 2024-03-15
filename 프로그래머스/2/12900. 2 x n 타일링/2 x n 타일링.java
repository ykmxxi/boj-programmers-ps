/*
    - 가로 길이가 2, 세로 길이가 1인 직사각형 타일 존재
    - 가로 길이가 n, 세로 길이가 2인 바닥을 가득 채우려고 한다
    - 타일 가로로 배치(가로 2, 세로 1), 세로로 배치(가로 1, 세로 2)
    - 가짜 문제: dy[i][0]: 길이 i에서 마지막 타일이 세로로 배치, dy[i][1]: 길이 i에서 마지막 타일이 가로로 배치
    - 진짜 문제: (dy[n][0] + dy[n][1]) % mod
    - 점화식
        - dy[i][0] = (dy[i - 1][0] + dy[i - 1][1]) % mod
        - dy[i][1] = (dy[i - 2][0] + dy[i - 2][1]) % mod
*/

class Solution {
    
    static final int MOD = 1_000_000_007;
    
    public int solution(int n) {
        int answer = 0;
        int[][] dy = new int[n + 1][2];
        
        dy[0][0] = 1; // 점화식을 위한 초기값 설정
        dy[1][0] = 1;
        
        for (int i = 2; i <= n; i++) {
            dy[i][0] = dy[i - 1][0] + dy[i - 1][1];
            dy[i][0] %= MOD;
            dy[i][1] = dy[i - 2][0] + dy[i - 2][1];
            dy[i][1] %= MOD;
        }
        
        answer = (dy[n][0] + dy[n][1]) % MOD;
        return answer;
    }
}
