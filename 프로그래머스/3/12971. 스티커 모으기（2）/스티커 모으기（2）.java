/*
    - N개의 스티커가 원형으로 연결
    - 스티커를 뜯어내어 적힌 숫자의 합이 최대가 되도록, 단 뜯어내면 양쪽으로 인접한 스티커는 사용 불가
    - 점화식
        첫 번째 스티커를 뗀 경우
        첫 번째 스티커를 떼지 않은 경우
        dy[i] = Math.max(dy[i - 1], dy[i - 2] + sticker[i])
*/

class Solution {
    
    public int solution(int sticker[]) {
        int answer = 0;
        int len = sticker.length;
        
        int[] dy = new int[len];
        
        if (len == 1) {
            return sticker[0];
        }
        
        // 먼저 0번 스티커를 뗀 경우
        dy[0] = sticker[0];
        dy[1] = dy[0];
        
        for (int i = 2; i < len - 1; i++) { // 마지막 스티커를 떼면 안됨
            dy[i] = Math.max(dy[i - 1], dy[i - 2] + sticker[i]);
        }
        
        answer = dy[len - 2];
        
        // 0번 스티커를 떼지 않은 경우
        dy = new int[len];
        dy[0] = 0;
        dy[1] = sticker[1];
        
        for (int i = 2; i < len; i++) { // 마지막 스티커를 떼도 된다
            dy[i] = Math.max(dy[i - 1], dy[i - 2] + sticker[i]);
        }

        return Math.max(answer, dy[len - 1]);
    }
    
}
