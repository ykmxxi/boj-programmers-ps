/*
    - 1 ~ number 까지 번호가 있음
    - 자신의 번호의 약수 개수에 해당하는 공격력을 가진 무기를 구매
    - 단, 제한 수치가 있고 제한 수치보다 큰 것을 구매하려면 power 수치를 구매
*/

class Solution {
    
    static int calc(int n) {
        int cnt = 0;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (i * i == n) {
                    cnt++;
                } else {
                    cnt += 2;
                }
            }
        }
        return cnt;
    }
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        for (int i = 1; i <= number; i++) {
            int n = calc(i);
            
            if (n <= limit) {
                answer += n;
            } else {
                answer += power;
            }
        }
        return answer;
    }
}