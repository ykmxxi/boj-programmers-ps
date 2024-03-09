/*
    - s, skip, 자연수 index 암호 
    규칙 1. s의 각 알파벳을 index만큼 뒤의 알파벳으로 바꾸기
    규칙 2. index 만큼의 뒤의 알파벳이 z를 넘어갈 경우 다시 a로
    규칙 3. skip에 있는 알파벳을 제외하고 건너뛴다
    - s = "aukks" skip = "wbqd" index = 5, 
*/

class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        
        for (char ch : arr) {
            
            for (int i = 1; i <= index; i++) {    
                ch += 1;
                if (ch > 'z') {
                    ch -= 26;
                }
                if (skip.contains(String.valueOf(ch))) {
                    i--;
                }
            }
            sb.append(ch);
        }
            
        return sb.toString();
    }
}