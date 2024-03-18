/*
    - 1 ~ n명 권투 경기는 1대1(간선)
    - 선수들의 순위를 매기려고 한다, 위상 정렬
    - indeg가 0 부터 시작해서 늦게 들어온 선수는 순위가 낮음
*/

import java.util.*;

class Solution {
    
    public int solution(int n, int[][] results) {
        int[][] graph = new int[n + 1][n + 1];
		for (int[] r : results) {
			graph[r[0]][r[1]] = 1;
			graph[r[1]][r[0]] = - 1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if(graph[i][k] == 1 && graph[k][j] == 1) {
						graph[i][j] = 1;
						graph[j][i] = -1;
					}
					
					if(graph[i][k] == -1 && graph[k][j] == -1) {
						graph[i][j] = -1;
						graph[j][i] = 1;
					}
				}
			}
		}
		
		int answer = 0;
		
		for(int i = 0 ; i <=n; i++) {
			int count = 0;
			for (int j = 0; j <= n; j++) {
				if(graph[i][j] != 0) count++;
			}
			if(count == n-1) answer++;
		}
        return answer;
    }
    
}