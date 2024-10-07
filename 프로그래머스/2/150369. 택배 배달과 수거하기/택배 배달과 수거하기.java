/*
    - n개의 집에 똑같은 크기의 상자에 담아 배달 다니며 빈 상자는 수거
    - cap개 실을 수 있음, i번째 집은 창고에서 i만큼 떨어짐
    - 배달할 개수와 빈 박스의 개수를 알 때, 트럭 하나로 모든 배달과 수거를 마치고 돌아올 수 있는
    최소 이동 거리
*/
public class Solution {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0L;
        int deliver = 0;
        int pickup = 0;

        for (int i = n - 1; i >= 0; i--) {
            // i번째 집에 배달/수거 박스가 없으면 방문 X
            if (deliveries[i] == 0 && pickups[i] == 0) {
                continue;
            }

            int count = 0; // 같은 집 왕복횟수
            while (deliver < deliveries[i] || pickup < pickups[i]) {
                count++;
                deliver += cap;
                pickup += cap;
            }
            deliver -= deliveries[i];
            pickup -= pickups[i];
            answer += (long) (i + 1) * count * 2;
        }
        return answer;
    }
    
}
