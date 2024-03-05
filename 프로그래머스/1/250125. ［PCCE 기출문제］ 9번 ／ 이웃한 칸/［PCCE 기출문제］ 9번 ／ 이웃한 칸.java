/*
    - 각 칸마다 색이 칠해진 2차원 격자 배열
    - 한 칸을 골랐을 때 상하좌우 중 같은 색깔로 칠해진 칸의 개수를 구하려고한다
*/

class Solution {
    
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        for (int k = 0; k < 4; k++) {
            int nx = h + dir[k][0];
            int ny = w + dir[k][1];
            
            if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) {
                continue;
            }
            if (board[h][w].equals(board[nx][ny])) {
                answer++;
            }
        }
        return answer;
    }
}