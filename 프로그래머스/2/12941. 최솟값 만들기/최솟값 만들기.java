/*  
    길이가 같은 배열 A, B 두개가 존재, 각 배열은 자연수로 이루어짐
    A, B에서 각각 한 개의 숫자를 뽑아 두 수를 곱한다. 이러한 과정을 배열의 길이만큼 반복하며, 두 수를 곱한 값을 누적해 더함
    최종적으로 누적된 값이 최소가 되도록 만드는 것이 목표
    k번째 숫자를 뽑았다면 k번째 숫자는 다시 뽑을 수 없음
    
    정답의 최대치: 모두 1000일 때 길이가 1000이면
    10^6이 10^3개 10^9(10억) -> int
*/

import java.util.*;

class Solution {
    public int solution(int []A, int []B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        
        for (int i = 0, j = A.length - 1; i < A.length; i++, j--) {
            answer += A[i] * B[j];
        }

        return answer;
    }
}
