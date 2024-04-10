/*  
    DP 문제
    - 진짜문제: 한 칸 or 2칸을 뛰어 n번째 칸에 도달하는 경우의 수
    - 가짜문제: 한 칸 or 2칸을 뛰어 i번째 칸에 도달하는 경우의 수 Dy[i]
    
    점화식
    - 마지막에 한 칸 or 두 칸 Dy[i] = Dy[i - 1] + Dy[i - 2]
    (A + B) % C = ((A % C) + (B % C)) % C

*/

class Solution {
    private static final int MOD = 1234567;
    
    public int solution(int n) {
        int[] Dy = new int[n + 1];
        
        Dy[0] = 1; // 점화식을 풀기 위해 설정한 가짜 답
        Dy[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            Dy[i] = Dy[i - 1] % MOD;
            Dy[i] += Dy[i - 2] % MOD;
            Dy[i] %= MOD;
        }
        
        return Dy[n];
    }
}