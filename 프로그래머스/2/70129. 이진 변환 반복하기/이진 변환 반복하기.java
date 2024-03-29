/*
    x의 모든 0을 제거
    x의 길이를 c라고 하면, c를 2진법으로 표현한 문자열로 변환
    s가 "1"이 될 때까지 반복
*/

class Solution {
    
    static int getLength(String s) {
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1') {
                len++;
            }
        }
        
        return len;
    }
    
    static String toBinary(int len) {
        return Integer.toBinaryString(len);
    }
    
    public int[] solution(String s) {
        int[] answer = new int[2]; // 이진 변환 횟수, 제거된 모든 0의 개수
        
        int len = 0;
        
        while (true) {
            // 0 제거
            len = getLength(s);
            answer[1] += s.length() - len;
        
            // 길이를 2진법으로 변환
            s = toBinary(len);
            answer[0]++;
            
            if (s.equals("1")) {
                break;
            }

        }
        
        return answer;
    }
}