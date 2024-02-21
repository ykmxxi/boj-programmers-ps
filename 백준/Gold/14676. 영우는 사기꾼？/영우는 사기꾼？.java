/*
    - 건물을 짓는 순서가 정해짐
    - 한 건물은 최대 3개의 건물에게만 영향을 미치도록 함
    - 모든 건물들은 중복 건설이 가능
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static List<Integer>[] adj;
    static int[] indeg, cnt, isBuild;
    static int[][] info;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        indeg = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x].add(y);
            indeg[y]++;
        }

        info = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            info[i][0] = x;
            info[i][1] = y;
        }
    }

    static void pro() {
        boolean flag = false;
        cnt = new int[N + 1]; // 현재 건설된 건물 개수
        isBuild = new int[N + 1]; // 선행 건물 건설 여부

        for (int i = 0; i < K; i++) {
            int bd = info[i][0];
            int num = info[i][1];

            if (bd == 1) { // 건설
                if (isBuild[num] < indeg[num]) { // 선행 건물이 모두 지어지지 않음
                    flag = true;
                    break;
                }

                cnt[num]++; // 건설

                if (cnt[num] == 1) { // 처음 건설했으면
                    for (int y : adj[num]) { // 후행 건물에게 알려준다
                        isBuild[y]++;
                    }
                }
            } else { // 파괴
                if (cnt[num] == 0) {
                    flag = true;
                    break;
                }

                cnt[num]--;

                if (cnt[num] == 0) {
                    for (int y : adj[num]) {
                        isBuild[y]--;
                    }
                }
            }
        }

        if (flag) {
            System.out.println("Lier!");
        } else {
            System.out.println("King-God-Emperor");
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
