/*
    - 연속 부분 수열에 같은 길이의 펄스 수열을 각 원소끼리 곱해 연속 펄스 부분 수열을 만듬
    - 펄스 수열: {1, -1, 1, -1, ...} or {-1, 1, -1, 1, ...}
    - 연속 펄스 부분 수열의 합 중 가장 큰 것을 return
    - dy[n] = max(dp[n-1] + a[n], a[n])
*/

class Solution {
    
    public long solution(int[] sequence) {
        long answer;
        
        int len = sequence.length;
        int[] a = new int[len];
        int[] b = new int[len];
        int n = 1;
        
        for(int i = 0; i < len; i++) {
            a[i] = sequence[i] * n;
            n *= -1;
            b[i] = sequence[i] * n;
        }

        long[] dyA = new long[len];
        long[] dyB = new long[len];
        
        dyA[0] = a[0];
        dyB[0] = b[0];
        answer = Math.max(dyA[0], dyB[0]);
    
        for(int i = 1; i < len; i++) {
            dyA[i] = Math.max(dyA[i-1] + a[i], a[i]);
            dyB[i] = Math.max(dyB[i-1] + b[i], b[i]);
    
            answer = Math.max(answer, Math.max(dyA[i], dyB[i]));
        }
        
        return answer;
    }
    
}
