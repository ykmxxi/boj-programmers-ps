/*
    - 땅의 높이를 모두 동일하게 땅 고르기 작업을 진행
    - N x M 집터, 땅 고르기 작업
        * (i, j)의 가장 위에 있는 블록을 제거해 인벤토리에 넣기 -> 2초
        * 인벤토리에서 블록 하나를 꺼내어 (i, j) 가장 위에 있는 블록 위에 놓는다 -> 1초
    - 땅 고르기 작업에 걸리는 최소 시간과 그 경우 땅의 높이를 출력
    - 집터 밖에서는 블록을 가져올 수 없고, 작업 시작 때 인벤토리에 B개의 블록이 존재하고, 땅의 높이는 256 초과 X or 음수 불가능
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final int LIMIT = 64_000_000;
    static int N, M, B, max = 0, min = 256;
    static int[][] A;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(A[i][j], max);
                min = Math.min(A[i][j], min);
            }
        }
    }

    static void pro() {
        int time = Integer.MAX_VALUE;
        int height = 0;
        for (int h = min; h <= max; h++) {
            int curTime = 0;
            int block = B;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (h < A[i][j]) { // 현재 땅이 만들 높이보다 크면 제거
                        curTime += (A[i][j] - h) * 2;
                        block += A[i][j] - h;
                    } else { // 현재 땅이 만들 높이보다 작으면 쌓기
                        curTime += (h - A[i][j]);
                        block -= (h - A[i][j]);
                    }
                }
            }

            if (block < 0) {
                break;
            }

            if (time >= curTime) {
                time = curTime;
                height = h;
            }
        }

        sb.append(time).append(' ').append(height);
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
