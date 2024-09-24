/*  
    - 구명보트는 한 번에 최대 2명씩 탈 수 있음
    - 모든 사람을 구출하기 위해 필요한 구명보트의 개수의 최소값을 return
*/

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people); // 오름차순으로 정렬
        
        int L = 0;
        int R = people.length - 1;
        
        while (L <= R) {
           if (people[L] + people[R] <= limit) {
               L++;
               R--;
               answer++;
           } else {
               R--;
               answer++;
           }
        }
        
        return answer;
    }
}
