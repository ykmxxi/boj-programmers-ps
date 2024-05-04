class Solution {
    public static String solution(String s, int n) {
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                builder.append(' '); // 공백인 경우 그대로 append
            } else {
                if (Character.isUpperCase(s.charAt(i))) { // 대문자인 경우
                    int index = (((int) s.charAt(i) - 65) + n) % 26;
                    builder.append((char) (65 + index));
                }
                if (Character.isLowerCase(s.charAt(i))) { // 소문자인 경우
                    int index = (((int) s.charAt(i) - 97) + n) % 26;
                    builder.append((char) (97 + index));
                }
            }
        }
        return builder.toString();
    }
}
