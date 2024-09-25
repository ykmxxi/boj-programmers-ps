/*
    - 정해진 예산 내 최대한 많은 부서의 물품을 구매
    - 정확히 신청한 금액만큼 지원
*/

import java.util.*;

class Solution {

    public int solution(int[] d, int budget) {
        int answer = 0;
        int total = 0;
        Arrays.sort(d);

        for (int amount : d) {
            total += amount;
            if (total <= budget) {
                answer += 1;
            } else {
                break;
            }

        }
        return answer;
    }

}
