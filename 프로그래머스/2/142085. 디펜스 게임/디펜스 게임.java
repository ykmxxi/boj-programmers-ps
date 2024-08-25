/*
    - n명으로 연속되는 적의 공격을 순서대로 막는 게임(순차적인 배열 -> 투 포인터?)
    - n명의 병사를 가지고 매 라운드마다 enemy[i] 적이 등장, enemy[i]명 만큼 소모해 막을 수 있음
        - e.g. 남은 병사 7, 적의 수 2 -> 해당 라운드를 막고 5명이 남음, 적의 수가 더 많으면 게임이 종료
    - 무적권 스킬을 사용하면 병사의 소모없이 한 라운드 막을 수 있음, 최대 k번
    - 몇 라운드까지 막을 수 있는지 return, 다 막으면 enemy의 길이 return
*/
import java.util.*;

class Solution {
    
    boolean flag = false;
    
    public int solution(int n, int k, int[] enemy) {
        // early return
        long total = 0;
        for (int a : enemy) {
            total += a;
        }
        if (k >= enemy.length || n >= total) { // 무적권을 사용해 다 막거나 병력이 모든 라운드의 적을 합친 것보다 크면 종료
            return enemy.length;
        }
        
        int ans = 0;
        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < enemy.length; i++) {
            n -= enemy[i];
            pq.offer(enemy[i]);
            
            if (n < 0) { // 현재 라운드를 막을 수 없다면
                if (k > 0) { // 무적권을 사용
                    n += pq.poll();
                    k--;
                } else { // 무적권을 사용하지 못하면
                    break;
                }
            }
            ans++;
        }
        return ans;
    }

}
