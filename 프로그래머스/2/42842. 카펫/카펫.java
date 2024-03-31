/*  
    - 중앙에는 노란색으로 칠해져 있고, 테두리 1줄은 갈색을 칠해져 있는 격자 모양 카펫
    - 카펫의 노란색과 갈색으로 색칠된 격자의 개수가 주어질 때, 카펫의 가로(열의 길이), 세로(행의 길이) 크기를 배열에 담아 return
*/

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        // answer[0] * answer[1] = brown + yellow
        // yellow = (answer[0] - 2) * (answer[1] - 2);
        // 3부터 시작
        
        int row = 3; // 행의 길이, answer[1]
        int col = 3; // 열의 길이, answer[0];
        
        while (true) {
            if (row * col > brown + yellow) { // 전체 갯수가 더 많아지면
                row++; // 행의 길이를 늘리고
                col = row; // 늘린 행의 길이부터 열의 길이를 다시 시작
            }
            
            int yc = (row - 2) * (col - 2);
            int bc = row * col - yc;
            
            if (yc == yellow && bc == brown) {
                answer[0] = col;
                answer[1] = row;
                break;
            }
            
            col++; // 가로 길이 늘리기
        }
        
        return answer;
    }
}