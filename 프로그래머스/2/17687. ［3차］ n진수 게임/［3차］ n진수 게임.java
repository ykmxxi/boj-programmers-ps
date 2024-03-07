/*
    - 둥글게 앉아서 숫자를 하나씩 차례대로 말하는 게임
    - 규칙 1. 숫자를 0부터 시작해 차례대로 말함 (첫 번째 0, 두 번쨰 1, ... 열 번째 9)
    - 규칙 2. 10 이상의 숫자부터는 한 자리씩 끊어서 말한다. (11 번째 1, 12 번째 0, 13 번째 1, 14 번째 1)
    - 위 게임을 이진수로 진행하기도 한다
*/

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        int number = 0;
        int cnt = 1;
        int order = 1;
        
        Loop1:
        while (true) {
            String num = Integer.toString(number, n); // number를 n진수로 
            
            for (int i = 0; i < num.length(); i++) {
                if (cnt > t) {
                    break Loop1;
                }
                if (order % m == p || (order % m == 0 && m == p)) { // 튜브의 순서이면
                    sb.append(Character.toUpperCase(num.charAt(i)));
                    cnt++;
                }
                order++;
            }
            
            number++;
        }
        
        return sb.toString();
    }
}