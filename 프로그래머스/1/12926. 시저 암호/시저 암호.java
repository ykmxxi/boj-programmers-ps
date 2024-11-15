/*
    - 거리만큼 밀어서 다른 알파벳으로 변경
    - "AB" 1 밀기 -> "BC", "z" 1 밀기 -> "a", 소문자는 밀면 다시 소문자
    - 공백은 밀어도 공백
*/
class Solution {
    
    public static String solution(String s, int n) {
        StringBuilder builder = new StringBuilder();
    
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                builder.append(' '); // 공백인 경우 그대로 append
            } else {
                if (Character.isUpperCase(s.charAt(i))) { // 대문자인 경우
                    int index = ((s.charAt(i) - 'A') + n) % 26;
                    builder.append((char) ('A' + index));
                }
                if (Character.isLowerCase(s.charAt(i))) { // 소문자인 경우
                    int index = (( s.charAt(i) - 'a') + n) % 26;
                    builder.append((char) ('a' + index));
                }
            }
        }
        return builder.toString();
    }
}
