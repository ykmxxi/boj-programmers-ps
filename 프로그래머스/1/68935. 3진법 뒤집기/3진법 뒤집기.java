class Solution {
    public int solution(int n) {
        int answer = 0;
        long ternary = 0L;
        long reverse = 0L;
        long digit = 1L;

        // 3진수로 변환
        do {
            ternary += (n % 3) * digit;
            n /= 3;
            digit *= 10;
        } while (n != 0);

        // 뒤집기
        while (ternary != 0) {
            reverse = reverse * 10 + ternary % 10;
            ternary /= 10;
        }

        // 10진수로 변환
        digit = 1L;
        do {
            answer += (reverse % 10) * digit;
            reverse /= 10;
            digit *= 3;

        } while (reverse != 0);

        return answer;
    }
}