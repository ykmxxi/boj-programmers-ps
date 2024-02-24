/*
    - N개의 곡을 연주, 볼륨을 바꾸고 연주
    - V[i] = i번째 곡을 연주하기 전에 바꿀 수 있는 볼륨
    - 즉, 현재 볼륨이 P -> i번째 곡을 연주하기 전 P + V[i] or P - V[i], 이 값은 0 ~ M
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, S, M;
    static int[] V;
    static boolean[][] dy;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        V = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }
    }

    static boolean isValid(int v) {
        return 0 <= v && v <= M;
    }

    static void pro() {
        dy = new boolean[N + 1][M + 1];

        dy[0][S] = true;

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            boolean can = false;
            ans = 0;

            for (int j = 0; j <= M; j++) {
                if (!dy[i - 1][j]) {
                    continue;
                }

                if (isValid(j - V[i])) {
                    dy[i][j - V[i]] = true;
                    can = true;
                    ans = Math.max(ans, j - V[i]);
                }
                if (isValid(j + V[i])) {
                    dy[i][j + V[i]] = true;
                    can = true;
                    ans = Math.max(ans, j + V[i]);
                }
            }

            if (!can) {
                ans = -1;
                break;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
