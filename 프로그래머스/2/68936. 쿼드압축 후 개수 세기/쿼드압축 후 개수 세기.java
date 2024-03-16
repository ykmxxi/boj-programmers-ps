/*
    - 0 or 1로 이루어진 2^n x 2^n 크기의 2차원 배열
    - 압축 방식
        1. 압축하고자 하는 특정 영역을 S라고 정의
        2. S 내부에 있는 모든 수가 같으면 해당 수 하나로 압축
        3. 모든 수가 같지 않으면 S를 4개의 균일한 정사각형 영역으로 쪼갠 뒤, 각 정사각형 영역에 대해 같은 압축 방식을 진행
*/

class Solution {
    
    int[] answer;
    
    boolean can(int x, int y, int s, int[][] arr) {
        for (int i = x; i < x + s; i++) {
            for (int j = y; j < y + s; j++) {
                if (arr[x][y] != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    void rec(int x, int y, int s, int[][] arr) {
        if (can(x, y, s, arr)) {
            if (arr[x][y] == 0) {
                answer[0]++;
            } else {
                answer[1]++;
            }
            return;
        }
        
        rec(x, y, s / 2, arr);
        rec(x + s / 2, y, s / 2, arr);
        rec(x, y + s / 2, s / 2, arr);
        rec(x + s / 2, y + s / 2, s / 2, arr);
        
    }
    
    public int[] solution(int[][] arr) {
        answer = new int[2];
        
        rec(0, 0, arr.length, arr);
        
        return answer;
    }
    
}
