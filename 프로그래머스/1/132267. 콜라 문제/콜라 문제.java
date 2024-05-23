/*
    빈 병 a개를 가져다주면 콜라 b병을 주는 마트가 있을 때
    빈 병 n개를 가져다주면 몇 병을 받을 수 있는지 계산하는 문제
    보유 중인 빈 병이 a개 미만이면, 추가적으로 빈 병을 받을 수 없다
    1 <= b < a <= n <= 1,000,000 (int)
*/

class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        // a개를 가져다주면 b병을 준다.
        while (n >= a) {
            int cnt = n / a;
            
            answer += cnt * b;
            n -= cnt * a;
            n += cnt * b;
        }
        
        return answer;
    }
}