import java.util.*;

class Solution {
    
    public int[] solution(int n) {
        int num = 1;
        int[][] arr = new int[n][n];
        
        int x = 0;
        int y = 0;
        int dir = 0;
        int limit = n % 2 == 0 ? (n + 1) * (n / 2) : (n + 1) * (n / 2) + (n + 1) / 2;
        int[] answer = new int[limit];
        
        while (num <= limit) {
            arr[x][y] = num;
            num++;
            
            if (dir % 3 == 0) { // 아래로
                if (x == n - 1 || arr[x + 1][y] != 0) { // 범위를 벗어나거나 또는 이미 방문한 점이라면
                    y++;
                    dir++;
                    continue;
                }
                x++;
            } else if (dir % 3 == 1) { // 오른쪽으로
                if (y == n - 1 || arr[x][y + 1] != 0) {
                    x--;
                    y--;
                    dir++;
                    continue;
                }
                y++;
            } else {
                if (arr[x - 1][y - 1] != 0) {
                    x++;
                    dir++;
                    continue;
                }
                x--;
                y--;
            }
        }
        
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    continue;
                }
                answer[idx] = arr[i][j];
                idx++;
            }
        }
        
        return answer;
    }
    
}
