/*
    - f(x) = x보다 크고 x와 비트가 1 ~ 2개 다른 수들 중에서 제일 작은 수
*/

class Solution {
    
    
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        
        for (int i = 0; i < numbers.length; i++) {
            answer[i] = numbers[i] + 1;
            answer[i] += (numbers[i] ^ answer[i]) >> 2;
        }
        
        return answer;
    }
}
