/*
    - 최소 공배수: 두 수를 곱한 결과 / 두 수의 최대 공약수
    - 최대 공약수: 분모를 분자로, 나머지 연산의 결과를 분모로 재귀
*/

class Solution {
    
    static int GCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return GCD(b, a % b);
    }
    
    static int LCM(int a, int b) {
        return a / GCD(a, b) * b;    
    }
    
    public int solution(int[] arr) {
        int answer = 0;
        
        if (arr.length == 1) {
            answer = arr[0];
        } else {
            answer = LCM(arr[0], arr[1]);
            for (int i = 2; i < arr.length; i++) {
                answer = LCM(answer, arr[i]);
            }
        }        
        return answer;
    }
    
}
