class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();

        if (n % 2 == 1) {    // 홀수
            for (int i = 0; i < n / 2; i++) {
                answer.append("수박");
            }
            answer.append("수");

        } else {    // 짝수
            for (int i = 0; i < n / 2; i++) {
                answer.append("수박");
            }

        }

        return answer.toString();
    }
}