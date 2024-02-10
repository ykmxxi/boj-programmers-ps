/*
    - 상하좌우 이동 배추흰지렁이
    - 총 몇 마리의 지렁이가 필요한지 구하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] limit = new int[3];
    static boolean[] possible = new boolean[201];
    static boolean[][][] visit = new boolean[201][201][201];

    static class State {

        int[] A;

        State(int[] A) {
            this.A = new int[3];
            for (int i = 0; i < 3; i++) {
                this.A[i] = A[i];
            }
        }

        State move(int from, int to, int[] limit) {
            int[] na = new int[]{A[0], A[1], A[2]};

            if (A[from] + A[to] > limit[to]) {
                na[from] -= limit[to] - na[to];
                na[to] = limit[to];
            } else {
                na[to] += na[from];
                na[from] = 0;
            }
            return new State(na);
        }

    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 3; i++) {
            limit[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void dfs(int a, int b, int c) {
        visit[a][b][c] = true;
        State state = new State(new int[]{a, b, c});

        if (state.A[0] == 0) { // A 물통이 비어있으면 체크
            possible[state.A[2]] = true;
        }

        for (int from = 0; from < 3; from++) {
            for (int to = 0; to < 3; to++) {
                if (from == to) {
                    continue;
                }

                State next = state.move(from, to, limit);

                if (!visit[next.A[0]][next.A[1]][next.A[2]]) {
                    dfs(next.A[0], next.A[1], next.A[2]);
                }
            }
        }


    }

    static void pro() {
        dfs(0, 0, limit[2]);
        for (int i = 0; i <= limit[2]; i++) {
            if (possible[i]) {
                sb.append(i).append(' ');
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
