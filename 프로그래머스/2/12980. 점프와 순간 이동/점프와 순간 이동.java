/*  
    - 한 번에 K 칸을 앞으로 점프 or (현재까지 온 거리) x 2 에 해당하는 위치로 순간이동 가능
    - 순간이동을 하면 건전지가 줄지 않고, K 칸 점프하면 K 만큼의 줄어든다
    - 거리가 n만큼 떨어져 있는 장소로 가려할 때, 건전지 사용량을 최소로(점프 횟수를 최소로)
    - 이동하려는 거리가 주어졌을 때, 사용해야 하는 건전지 사용량의 최소값을 return
*/

import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        
        while (n != 0) {
            if (n % 2 == 1) {
                answer++;
            }
            
            n /= 2;
        }

        return answer;
    }
}
