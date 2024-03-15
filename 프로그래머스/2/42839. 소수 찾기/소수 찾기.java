/*
    - 가능한 수의 범위: 0 ~ 9,999,999
    1. 0 ~ 9,999,999 까지 소수인 것을 구해놓기 (에라토스테네스의 체)
    2. 순열로 가능한 숫자 얻기
    3. 소수 체크
*/

import java.util.*;

class Solution {
    
    static int[] prime;
    static Set<Integer> set;
    static boolean[] visit;
    
    void getPrime() {
        
        prime = new int[10_000_000];
        
        prime[0] = 1; // 1이면 소수가 아님
        prime[1] = 1;
        
        for (int i = 2; i <= Math.sqrt(9_999_999); i++) {
            if (prime[i] == 0) {
                for (int j = i * i; j < prime.length; j += i) {
                    prime[j] = 1;
                }
            }
        }
    }
    
    void rec(char[] arr, String s, int depth) {
        if (depth > arr.length) {
            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                
                String next = s + arr[i];
                set.add(Integer.parseInt(next));
                
                rec(arr, next, depth + 1);
                
                visit[i] = false;
            }
        }
        
    }
    
    
    public int solution(String numbers) {
        int answer = 0;
        
        getPrime();
        
        set = new HashSet<>();
        visit = new boolean[8];
        rec(numbers.toCharArray(), "", 0);
            
        for (int num : set) {
            if (prime[num] == 0) {
                answer++;
            }
        }
        
        return answer;
    }
}