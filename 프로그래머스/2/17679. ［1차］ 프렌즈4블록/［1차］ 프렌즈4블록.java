/*
    - 같은 모양의 블록이 2x2 형태로 4개가 붙어있으면 사라지면서 점수를 얻음
    - 지워지는 조건에 만족하는 2x2 타일이 여러 개 있다면 한꺼번에 지워짐
    - 지워진 후 블록이 아래로 떨어져 빈 공간을 채움
    - 위 과정을 반복해 지워지는 블록이 모두 몇 개인지 구하기
*/

import java.util.*;

class Solution {
    int[][] dir = {{1, 0}, {0, 1}, {1, 1}};
    
    public int solution(int m, int n, String[] board) {
        char[][] arr = new char[m][n];
        int answer = 0;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                arr[i][j] = board[i].charAt(j);
            }
        }
        
        while (true) {
            boolean[][] used = new boolean[m][n];
            boolean find = false;
            
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (arr[i][j] == '0') { // 빈벽이면 넘어가기
                        continue;
                    }
                    int cnt = 1;
                    for (int k = 0; k < 3; k++) {
                        int nx = i + dir[k][0];
                        int ny = j + dir[k][1];
                        if (arr[i][j] == arr[nx][ny]) {
                            cnt++;
                        }
                    }
                    
                    if (cnt == 4) { // 2x2 찾았으면
                        find = true;
                        if (!used[i][j]) {
                            used[i][j] = true;
                            answer++;
                        }
                        for (int k = 0; k < 3; k++) {
                            int nx = i + dir[k][0];
                            int ny = j + dir[k][1];
                            
                            if (!used[nx][ny]) {
                                used[nx][ny] = true;
                                answer++;
                            }
                        }
                    }   
                }
            }
            
            // 블록 아래로 내리기
            for (int i = 0; i < n; i++) {
                Queue<Character> q = new LinkedList<>();
                int cnt = 0;
                for (int j = 0; j < m; j++) {
                    if (used[j][i]) { // 사라진 블록이면 dq 제일 앞으로
                        cnt++;
                        continue;
                    }
                    q.offer(arr[j][i]);
                }
                
                for (int j = 0; j < m; j++) {
                    if (cnt > 0) {
                        arr[j][i] = '0';
                        cnt--;
                    } else {
                        arr[j][i] = q.poll();
                    }
                }
                
            }
            
            if (!find) {
                break;
            }
        }
        return answer;
    }
    
}
