/*
    - 페인트가 칠해진 길이가 n미터 벽,
    - 벽의 구역을 나누어 일부만 칠해 예산을 최소화
    - 벽을 길이가 1미터인 구역 1 ~ n 구역으로 나누고 다시 칠해야 할 구역을 정했음
    - 벽을 칠하는 룰러의 길이는 m, 룰러가 벽에서 벗어나면 X, 구역의 일부분만 포함되도록 칠하기 X(구역 전체를 칠해야 함)
    - 다시 칠하기로 정한 구역은 적어도 한 번 페인트칠 해야 함, 같은 구역 여러번 가능
    - section이 오름차순 -> 슬라이딩 윈도우
*/

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        boolean[] visit = new boolean[n + 1]; // 1 ~ n 한번이라도 칠한 여부
        
        for (int sec : section) {
            if (visit[sec]) {
                continue;
            }
            
            int start = sec;
            int end = sec + m - 1;
            
            if (end > n) {
                start -= (end - n);
                end = n;
            }
            
            for (int i = start; i <= end; i++) {
                visit[i] = true;
            }
            answer++;
        }
        
        return answer;
    }
    
}