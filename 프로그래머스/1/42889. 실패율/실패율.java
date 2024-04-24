/*  
    실패율: 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
    전체 스테이지의 개수 N, 게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담힌 stages가 주어짐
    실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return
    
    1 <= N <= 500
    1 <= stages의 길이 <= 200,000
    1 <= stages의 값 <= N + 1
        각 값은 사용자가 현재 도전 중인 스테이지의 번호
        N + 1: 마지막 스테이지 까지 클리어 한 사용자
    실패율이 같으면 작은 번호의 스테이지가 먼저 오도록
    스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0
*/

import java.util.*;

class Solution {
    
    static class Fail implements Comparable<Fail> {
        int stage;
        double rate;
        
        public Fail(int stage, double rate) {
            this.stage = stage;
            this.rate = rate;
        }
        
        @Override
        public int compareTo(Fail o) {
            if (this.rate == o.rate) { // 실패율이 같으면
                return this.stage - o.stage; // 작은 번호의 스테이지가 먼저 (오름차순)
            }
            
            return Double.compare(o.rate, this.rate); // 실패율 내림차순
        }
    }
    
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        Fail[] fails = new Fail[N];
        
        for (int i = 0; i < N; i++) {
            int nonClear = 0;
            int playerNum = 0;
            
            for (int j = 0; j < stages.length; j++) {
                if (stages[j] == i + 1) {
                    nonClear++;
                }
                if (stages[j] >= i + 1) {
                    playerNum++;
                }
            }
            
            if (playerNum == 0) {
                fails[i] = new Fail(i + 1, 0);
                continue;
            }
            
            fails[i] = new Fail(i + 1, (double)nonClear / playerNum);
        }
        
        Arrays.sort(fails);
        
        for (int i = 0; i < N; i++) {
            answer[i] = fails[i].stage;
        }
        
        return answer;
    }
}