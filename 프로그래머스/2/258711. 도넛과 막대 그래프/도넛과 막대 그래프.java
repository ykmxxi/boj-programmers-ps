/*
    - 도넛 모양, 막대 모양, 8자 모양 그래프: 1개 이상의 정점과, 단방향 간선(방향 그래프)
    - 도넛 모양: 사이클 존재, n개의 정점과 n개의 간선, n - 1개의 정점을 한 번씩 방문하면 출발점으로 돌아옴
    - 막대 모양: n개의 정점과 n - 1개의 간선, 트리 모양
    - 8자 모양: 2n + 1개의 정점과 2n + 2개의 간선, 2개의 도넛 모양 그래프에서 정점을 하나씩 골라 결합
    - 여러 개의 그래프가 주어질 때 무관한 정점을 하나 생성한 뒤 각 그래프의 임의의 정점 하나로 향하는 간선을 연결
    - 그 후 번호를 매김, 간선 정보가 주어지면 생성한 정점의 번호와 정점을 생성하기 전 각 그래프의 수를 구하기
*/

import java.util.*;

class Solution {
    
    static List<Integer>[] adj;
    static int[] indeg;
    static int vc, idx, cnt1, cnt2, cnt3;
    static boolean[] visit;
    static boolean f1, f2;
    
    static void makeAdj(int[][] e) {
        // 정점 갯수 찾기
        for (int[] a : e) {
            vc = Math.max(vc, Math.max(a[0], a[1]));
        }
        
        adj = new ArrayList[vc + 1];
        indeg = new int[vc + 1];
        for (int i = 1; i <= vc; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] a : e) {
            int from = a[0];
            int to = a[1];
            
            indeg[to]++;
            adj[from].add(to);
        }
    }
    
    static void findVertex() {
        for (int i = 1; i <= vc; i++) {
            if (adj[i].size() >= 2 && indeg[i] == 0) {
                idx = i;
                break;
            }
        }
    }
    
    static void dfs1(int x, int start) {
        visit[x] = true;
        
        if (adj[x].size() != 1) {
            return;
        }
        for (int y : adj[x]) {
            if (y == start && adj[x].size() == 1) {
                cnt1++;
                break;
            }
            dfs1(y, start);
        }
    }
    
    static void find1() { // 도넛 모양 찾기
        visit = new boolean[vc + 1];
        
        for (int i = 1; i <= vc; i++) {
            if (i == idx) {
                continue;
            }
            if (visit[i]) {
                continue;
            }
            dfs1(i, i);
        }
    }
    
    static void dfs2(int x, int depth, int cnt) {
        visit[x] = true;
        
        if (adj[x].isEmpty() && depth == cnt - 1) {
            cnt2++;
            return;
        }
        if (adj[x].size() != 1) {
            return;
        }
        for (int y : adj[x]) {
            if (visit[y]) {
                continue;
            }
            dfs2(y, depth + 1, cnt + 1);
        }
        
    }
    
    static void find2() { // 막대 모양 찾기
        visit = new boolean[vc + 1];
        
        for (int i = 1; i <= vc; i++) {
            if (i == idx) {
                continue;
            }
            if (visit[i]) {
                continue;
            }
            dfs2(i, 0, 1);
        }
    }
    
    static void dfs3(int x, int start, boolean f) {
        visit[x] = true;
        
        if (adj[x].size() != 1) {
            return;
        }
        for (int y : adj[x]) {
            if (y == start && adj[x].size() == 1) {
                if (f) {
                    f1 = true;
                }
                f2 = true;
                break;
            }
            dfs3(y, start, f);
        }
    }
    
    static boolean check(int x) {
        dfs3(adj[x].get(0), x, true);
        dfs3(adj[x].get(1), x, false);
        
        return f1 && f2;
    }
    
    static void find3() { // 8자 모양 찾기
        visit = new boolean[vc + 1];
        
        for (int i = 1; i <= vc; i++) {
            if (i == idx) {
                continue;
            }
            if (adj[i].size() == 2) {
                f1 = false;
                f2 = false;
                
                if (check(i)) {
                    cnt3++;
                }
            }
        }
    }
    
    public int[] solution(int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        
        makeAdj(edges);
        findVertex(); // 추가된 정점 찾기
        find1();
        find2();
        find3();
        
        ans.add(idx);
        ans.add(cnt1);
        ans.add(cnt2);
        ans.add(cnt3);
        System.out.println(idx);
        System.out.println(cnt1);
        System.out.println(cnt2);
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
}