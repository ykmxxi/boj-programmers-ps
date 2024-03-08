/*
    - 먼저 첫 글자를 읽고 x에 저장
    - 이 문자열을 왼쪽에서 오른쪽으로 읽어가면서 x와 x가 아닌 다른 글자들이 나온 횟수를 센다 (처음 x도 센다)
    - 처음으로 두 횟수가 같아지는 순간 멈추고 지금까지 읽은 문자열을 분리
    - 나머지 분자열을 빼고 남은 부분에 대해서 이 과정을 반복
    - 두 횟수가 다른 상태에서 더 읽을 문자열이 없으면 나머지를 분리하고 종료
    - 분해한 문자열의 개수를 return
*/

class Solution {
    public int solution(String s) {
        int answer = 0;
        char prev = '1';
        int same = 0;
        int diff = 0;
        
        for (char ch : s.toCharArray()) {
            if (prev == '1') {
                prev = ch;
                same++;
                answer++;
            } else if (ch == prev) {
                same++;
            } else {
                diff++;
            }
            
            if (same == diff) {
                prev = '1';
                same = 0;
                diff = 0;
            }
        }
        
        return answer;
    }
}