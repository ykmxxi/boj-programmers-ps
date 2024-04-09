import java.util.*;

class Solution {
    
    static int min;
    
    void rotate(int[][] arr, int sx, int sy, int ex, int ey) {
        int n = arr[sx][ey];
        min = n;
        
        // 숫자를 우로 이동 (상단)
        for(int i = ey - 1; i >= sy; i--){
            min = Math.min(min, arr[sx][i]);
            arr[sx][i + 1] = arr[sx][i];
        }

        // 숫자를 위로 이동 (좌측)
        for(int i = sx + 1; i <= ex; i++){
            min = Math.min(min, arr[i][sy]);
            arr[i - 1][sy] = arr[i][sy];
        }

        // 숫자를 좌로 이동 (하단)
        for(int i = sy + 1; i <= ey; i++){
            min = Math.min(min, arr[ex][i]);
            arr[ex][i-1] = arr[ex][i];
        }

        // 숫자를 아래로 이동 (우측)
        for(int i = ex - 1; i >= sx; i--){
            min = Math.min(min, arr[i][ey]);
            arr[i + 1][ey] = arr[i][ey];
        }
        
        arr[sx + 1][ey] = n;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        List<Integer> ans = new ArrayList<>();
        
        int[][] arr = new int[rows][columns];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = num;
                num++;
            }
        }
        
        for (int[] query : queries) {
            int sx = query[0] - 1;
            int sy = query[1] - 1;
            int ex = query[2] - 1;
            int ey = query[3] - 1;
            
            rotate(arr, sx, sy, ex, ey);
            
            ans.add(min);
        }
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
    
}
