/*
- 더하기는 결합법칙 성립, 빼기는 성립하지 않음
    - 1-5-3 -> ((1-5)-3) = -7, (1 -(5-3)) = -1
- 1 - 3 + 5 - 8 -> 5가지 (덧셈은 결합법칙 성립, 즉 언제 해도 값이 똑같음)
- 크기: 10^3 숫자가 +로 201이면 -> 
*/

import java.util.*;

class Solution {
    
    static int[] num;
    static String[] oper;
    static int[][] max; // 구간 i ~ j 최대값
    static int[][] min; // 구간 i ~ j 최소값

    public int solution(String arr[]) {
        // 초기화
        num = new int[arr.length / 2 + 1];
        oper = new String[arr.length / 2];
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                num[i / 2] = Integer.parseInt(arr[i]);
            } else {
                oper[i / 2] = arr[i];
            }
        }
        
        // 구간을 나누고, 구간을 연결하는 연산자가
        // +인 경우: max + max -> 최대값 후보 / min + min -> 최소값 후보
        // -인 경우: max - min -> 최대값 후보 / min - max -> 최소값 후보
        int len = num.length;
        max = new int[len][len];
        min = new int[len][len];
        
        // 초기 값: 길이 1의 구간(자기 자신)
        for (int i = 0; i < len; i++) {
            max[i][i] = num[i];
            min[i][i] = num[i];
        }
        
        for (int i = 2; i <= len; i++) {
            for (int j = 0; j + i - 1 < len; j++) {
                int k = j + i - 1; // 구간
                max[j][k] = Integer.MIN_VALUE;
                min[j][k] = Integer.MAX_VALUE;
                
                for (int l = j; l < k; l++) {
                    if (oper[l].equals("+")) {
                        max[j][k] = Math.max(max[j][k], max[j][l] + max[l + 1][k]);
                        min[j][k] = Math.min(min[j][k], min[j][l] + min[l + 1][k]);
                    } else {
                        max[j][k] = Math.max(max[j][k], max[j][l] - min[l + 1][k]);
                        min[j][k] = Math.min(min[j][k], min[j][l] - max[l + 1][k]);
                    }
                }
            }
        }
        
        // 0 ~ len 구간의 최대값
        return max[0][len - 1];
    }
}
