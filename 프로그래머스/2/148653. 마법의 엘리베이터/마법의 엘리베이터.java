/*
    - -1, +1, -10, +10, -100, +100 과 같이 절대값이 10^c 형태인 정수들이 적힌 버튼들이 존재
    - 버튼을 누르면 현재 층 수에 버튼에 적혀 있는 값을 더한 층으로 이동
    - 더한 값이 0 미만이면 움직이지 않음
    - 최소한의 버튼을 눌러 0으로 가기
*/

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (true) {
            if (storey == 0) {
                break;
            }
            
            int n = storey % 10;
            storey /= 10;
            
            if (n == 5) {
                if (storey % 10 >= 5) { // 앞자리가 5 이상이면 + 버튼을 누르는게 더 빠름
                    answer += 10 - n;
                    storey++;
                } else {
                    answer += n;
                }
            } else if (n < 5) {
                answer += n;
            } else {
                answer += 10 - n;
                storey++;
            }
        }
        return answer;
    }
}