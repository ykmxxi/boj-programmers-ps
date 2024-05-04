/*
    N / 2 마리를 가져가도 좋다
    포켓몬은 종류에 따라 번호를 붙여 구분
    같은 종류의 포켓몬은 같은 번호
    N개 중 N / 2개를 선택할 때 가질 수 있는 최대 가지수
    정답의 최대치: 10,000 (int)
*/

import java.util.*;

class Solution {
    public int solution(int[] nums) {
        HashMap<Integer, Integer> pocketMon = new HashMap<>();
        int len = nums.length;
        
        for (int num : nums) {
            pocketMon.put(num, 1);
        }
        
        return pocketMon.size() > len / 2 ? len / 2 : pocketMon.size();
    }
}