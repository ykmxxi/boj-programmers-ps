/*
    - 100 x 100 도화지에 10 x 10 도화지를 붙임
    - 한 장 또는 여러 장 붙인 후 색종이가 붙은 영역의 넓이를 구하기
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] infos; // 색종이 정보
    static boolean[][] visit;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        infos = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            infos[i][0] = Integer.parseInt(st.nextToken()); // x
            infos[i][1] = Integer.parseInt(st.nextToken()); // y
        }
        visit = new boolean[101][101];
    }

    static void pro() {
        int ans = 0;
        for (int[] info : infos) {
            int sx = info[0];
            int sy = info[1];

            for (int x = sx; x < sx + 10; x++) {
                for (int y = sy; y < sy + 10; y++) {
                    if (!visit[x][y]) {
                        visit[x][y] = true;
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
