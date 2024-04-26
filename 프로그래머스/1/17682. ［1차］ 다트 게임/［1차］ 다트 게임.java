/*  
    다트 게임: 다트판에 다트를 세 차례 던져 그 점수의 합계로 실력을 겨루는 게임
    
    점수 계산 로직
    1. 3번의 기회
    2. 각 기회마다 얻을 수 있는 점수 0 ~ 10
    3. Single(S), Double(D), Triple(T) 영역이 존재, 각 영역 당첨 시 점수에서 1제곱, 2제곱, 3제곱
    4. 옵션: *(스타상), #(아차상)
        스타상: 해당 점수와 바로 전에 얻은 점수를 각 2배로 만듦
        아차상: 해당 점수를 마이너스
    5. *(스타상)은 첫 번째 기회에서 나올 수도 있음 -> 첫 번째의 점수만 2배가 됨
    6. 스타상의 효과는 다른 스타상의 효과와 중첩 가능 -> 이 경우 점수가 4배가 됨
    7. 스타상의 효과는 아차상의 효과와 중첩 가능 -> 아차상의 점수는 -2배가 됨
    8. 각 점수마다 영역이 하나씩 존재
    9. 스타상, 아차상은 점수마다 둘 중 하나만 존재할 수 있고, 존재하지 않을 수 있음
*/

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        
        int[] scores = new int[3];
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < dartResult.length(); i++) {
            char ch = dartResult.charAt(i);
            
            if (ch >= '0' && ch <= '9') { // 숫자인 경우
                sb.append(ch);
            } else if (ch == 'S' || ch == 'D' || ch == 'T') {
                if (ch == 'S') {
                    scores[idx] = (int)(Math.pow(Integer.parseInt(sb.toString()), 1));
                }
                
                if (ch == 'D') {
                    scores[idx] = (int)(Math.pow(Integer.parseInt(sb.toString()), 2));
                }
                
                if (ch == 'T') {
                    scores[idx] = (int)(Math.pow(Integer.parseInt(sb.toString()), 3));
                }
                
                sb = new StringBuilder();
                idx++;

            } else { // 옵션인 경우
                if (ch == '*') {
                    scores[idx - 1] *= 2;
                    if (idx >= 2) {
                        scores[idx - 2] *= 2;
                    }
                }
                
                if (ch == '#') {
                    scores[idx - 1] *= -1;
                }
            }
               
        }
        
        for (int score : scores) {
            answer += score;
        }
        
        return answer;
    }
}