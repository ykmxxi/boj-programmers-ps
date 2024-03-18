/*
    - 오름차순으로 정렬된 수열
    - 순서를 지키며 부분 수열의 합이 k, 여러개인 경우 가장 짧은 수열 찾기
    - 
*/

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int sum = 0;
        int min = Integer.MAX_VALUE;
        
        for (int L = 0, R = 0; L < sequence.length; L++) {
            while (R < sequence.length && sum < k) {
                sum += sequence[R];
                R++;
            }
            
            if (sum == k) {
                int len = R - L - 1;
                
                if (min > len) {
                    min = len;
                    answer[0] = L;
                    answer[1] = R - 1;
                }
            }
            
            sum -= sequence[L];
        }
        
        return answer;
    }
    
}
