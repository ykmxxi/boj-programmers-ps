/*
    - 두 수의 짝꿍: x, y의 임의의 자리에서 공통으로 나타나는 정수(0 ~ 9) k들을 이용해 만들 수 있는 가장 큰 정수
    - 짝꿍이 존재하지 않으면 -1, 짝꿍이 0으로만 구성되어 있다면 0
*/

import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        StringBuilder sb = new StringBuilder();
    
        int[] vx = new int[10];
        int[] vy = new int[10];
        
        for (char c : X.toCharArray()) {
            vx[c - '0']++;
        }
        for (char c : Y.toCharArray()) {
            vy[c - '0']++;
        }
        
        for (int i = 9; i >= 0; i--) {
            if (vx[i] > 0 && vy[i] > 0) {
                for (int j = 0; j < Math.min(vx[i], vy[i]); j++) {
                    sb.append(i);
                }
            }
        }
        
        if (sb.toString().isEmpty()) {
            return "-1";
        }
        
        return sb.toString().charAt(0) == '0' ? "0" : sb.toString();
        
    }
}