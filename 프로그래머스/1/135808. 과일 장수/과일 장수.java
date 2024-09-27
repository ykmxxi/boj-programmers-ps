/*
    - 사과는 1점부터 k점 까지의 점수로 분류, k점: 최상 상품, 1점: 최하품
    - 한 상자에 사과를 m개씩 담고, 가장 낮은 점수가 p점인 경우 사과 한 상자의 가격은 p * m
    - 상자 단위로 팔았을 때 얻을 수 있는 최대 이익
     [4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2]	
      -> 4 4 4 4 4 4 2 2 2 2 1 1
*/
import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        Arrays.sort(score);
        
        int cnt = 0;
        int cheap = 0;
        
        for (int i = score.length - 1; i >= 0; i--) {
            if (cnt == m) {
                answer += cheap * m;
                cnt = 0;
            }
            cheap = score[i];
            cnt++;
        }
        
        if (cnt == m) {
            answer += cheap * m;
        }
    
        return answer;
    }
    
}
