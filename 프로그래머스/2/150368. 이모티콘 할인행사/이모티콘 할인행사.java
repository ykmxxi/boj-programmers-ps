/*
    - 행사 목적: 이모티콘 가입자 최대한 늘리기 우선, 판매액 최대한 늘리기 다음
    - n명 m개 판매, 할인율은 10, 20, 30, 40 중 하나
    - 기준에 따라 일정 비율 이상 할인하는 이모티콘 모두 구매 -> 비용 합이 일정 가격 이상이면 구매를 모두 취소하고 플러스 서비스 가입
    - 행사 목적을 최대한 달성했을 때 플러스 서비스 가입 수, 매출액 반환
    - 1 <= users <= 100, 1 <= 이모티콘 <= 7
*/
class Solution {
    
    static int[] discount = {10, 20, 30, 40};
    static int sub;
    static int co;
    
    public int[] solution(int[][] users, int[] emoticons) {
        findResult(0, emoticons.length, new int[emoticons.length], users, emoticons);
        return new int[]{sub, co};
    }

    public void findResult(int index, int emoticonsLength, int[] discounts, int[][] users, int[] emoticons) {
        if (index == emoticonsLength) {
            int subscribe = 0;
            int cost = 0;

            for (int[] user: users) {
                int userDiscountRate = user[0];
                int userMaxCost = user[1];
                int sum = 0;

                for (int i = 0; i < emoticons.length; i++) {
                    if (discounts[i] >= userDiscountRate) {
                        sum += emoticons[i] / 100 * (100-discounts[i]);
                    }
                }
                
                if (sum >= userMaxCost) {
                    subscribe++;
                } else {
                    cost+=sum;
                }
            }
            
            if (subscribe > sub) {
                sub = subscribe;
                co = cost;
            } else if (subscribe == sub) {
                co = Math.max(co, cost);
            }
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            discounts[index] = discount[i];
            findResult(index+1, emoticonsLength, discounts, users, emoticons);
        }

    }
    
}
