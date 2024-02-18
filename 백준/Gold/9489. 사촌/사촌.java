/*
    - 첫 번째 정수는 트리의 루트 노드
    - 다음 등장하는 연속된 수의 집합은 루트의 자식, 이 집합에 포함되는 수의 첫 번째 수는 루트 노드 + 1
    - 모든 연속된 수의 집합은 아직 자식이 없는 노드의 자식이 된다
    - 집합은 수가 연속하지 않은 곳에서 구분
    - 수열 특정 노드 번호 k가 주어졌을 때 k의 사촌의 수를 구하는 문제
    - 사촌: 부모는 다르고 부모의 부모(조부모)가 같은 경우
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, K, kIdx;
    static int[] A, parent;

    static void input(String nk) throws IOException {
        StringTokenizer st = new StringTokenizer(nk, " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (A[i] == K) {
                kIdx = i;
            }
        }
    }

    static void pro() {
        boolean[] visit = new boolean[N];

        parent = new int[N];
        parent[0] = -1; // 루트의 부모 -1
        int lastParentIdx = 0;

        for (int i = 1; i < N; i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            parent[i] = lastParentIdx;
            for (int j = i + 1; j < N; j++) {
                if (A[j] == A[j - 1] + 1) {
                    visit[j] = true;
                    parent[j] = lastParentIdx;
                } else {
                    lastParentIdx++;
                    break;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (i == kIdx) {
                continue;
            }
            if (parent[kIdx] != parent[i] && parent[parent[kIdx]] == parent[parent[i]]) { // 부모는 다르고, 조부모는 같고
                ans++;
            }
        }

        sb.append(ans).append('\n');
    }

    public static void main(String[] args) throws IOException {
        String nk = " ";

        while (!(nk = br.readLine()).equals("0 0")) {
            input(nk);
            pro();
        }

        System.out.print(sb);
    }

}
