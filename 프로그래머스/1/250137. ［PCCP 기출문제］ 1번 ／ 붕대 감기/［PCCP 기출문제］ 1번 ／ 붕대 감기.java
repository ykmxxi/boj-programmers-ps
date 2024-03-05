/*
    - 붕대 감기: t초 동안 붕대를 감으면서 1초마다 x 체력 회복, t초 연속으로 붕대 감기 성공하면 y만큼 체력을 추가로 회복
    - 최대 체력보다 커지는 것은 불가능
    - 공격을 받으면 기술이 취소, 공격을 받거나 기술이 끝나면 연속 성공 시간이 다시 0으로 초기화
    - 모든 공격이 끝난 후 남은 체력, 공격을 받아 체력이 0 이하가 되면 -1 return
*/

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int end = attacks[attacks.length - 1][0];
        
        int time = 0;
        int curHealth = health;
        int suc = 0;
        int idx = 0;
        while (time <= end) {
            
            if (attacks[idx][0] == time) { // 공격을 당하면
                suc = 0;
                curHealth -= attacks[idx][1];
                
                idx++; // 다음 공격으로 update
            } else { // 몬스터 공격 시간이 아니면
                suc++; // 연속 성공 시간 증가
                if (curHealth + bandage[1] < health) { // 체력 회복
                    curHealth += bandage[1];
                } else {
                    curHealth = health;
                }
            }
            
            if (curHealth <= 0) {
                answer = -1;
                break;
            }
            if (suc == bandage[0]) {
                curHealth += bandage[2];
                if (curHealth > health) {
                    curHealth = health;
                }
                suc = 0;
            }
            
            time++;
            answer = curHealth;
        }
        
        return answer;
    }
}