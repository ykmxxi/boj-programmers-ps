import java.util.*;

class Solution {
    
    static boolean isPrime(String str) {
        long num = Long.parseLong(str);
        
        for (long i = 2L; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public int solution(int n, int k) {
        int answer = 0;
        
        String str = Integer.toString(n, k); // n을 k진수로 변환
        String[] arr = str.split("0");
        
        for (String s : arr) {
            if (s.equals("1") || s.equals("")) {
                continue;
            }
            if (isPrime(s)) {
                answer++;
            }
        }

        return answer;
    }
}