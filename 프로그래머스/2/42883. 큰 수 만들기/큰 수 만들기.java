/*
    - 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자 구하기
*/

class Solution {
    
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        
        int s = 0;
        
        for (int i = 0; i < number.length() - k; i++) {
            char max = '0';
            
            for (int j = s; j <= i + k; j++) {
                if (max < number.charAt(j)) {
                    max = number.charAt(j);
                    s = j + 1;
                }
            }
            
            sb.append(max);
            
        }
        
        return sb.toString();
    }
    
}
