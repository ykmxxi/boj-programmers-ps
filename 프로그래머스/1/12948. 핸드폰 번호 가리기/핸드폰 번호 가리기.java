class Solution {
    
    public String solution(String phone_number) {
        String answer = "";
        char[] chArr = new char[phone_number.length()];
        for (int i = 0; i < phone_number.length(); i++) {
            if (i < (phone_number.length() - 4))
                chArr[i] = '*';
            else
                chArr[i] = phone_number.charAt(i);
        }

        answer = new String(chArr);
        return answer;
    }
    
}
