/*
    네트워크: 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태
    - A-B, B-C 이면 A-C도 간접적으로 연결되어 정보 교환 가능
    - A, B, C는 모두 같은 네트워크 상에 있다
    
    네트워크의 개수(group 의 개수)
*/
import java.util.*;

class Solution {
    static ArrayList<Integer>[] adj;
    static int[] visit;
    
    static void dfs(int start) {
        visit[start] = 1;
        
        for (int next : adj[start]) {
            if (visit[next] == 1) {
                continue;
            }
            
            dfs(next);
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers[i].length; j++) {
                if (i == j) {
                    continue;
                }
                
                if (computers[i][j] == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        visit = new int[n];
        for (int i = 0; i < n; i++) {
            if (visit[i] == 0) {
                dfs(i);
                answer++;
            }
        }
        
        return answer;
    }
}