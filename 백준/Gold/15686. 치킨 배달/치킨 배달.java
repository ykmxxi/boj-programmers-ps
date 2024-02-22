/*
    - N x N 도시, 각 칸은 빈 칸(0) or 치킨집(2) or 집(1)
    - 치킨 거리 = 집과 가장 가까운 치킨집 사이의 거리
    - 도시의 치킨 거리 = 모든 집의 치킨 거리의 합
    - 가장 수익을 많이 낼 수 있는 치킨집의 개수는 최대 M개, M개를 골라 나머지를 폐업시키고 도시의 치킨 거리가 가장 작게
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, ans = Integer.MAX_VALUE;
    static int[] sel;
    static List<Info> homes, stores;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        homes = new ArrayList<>();
        stores = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                String s = st.nextToken();

                if (s.equals("1")) {
                    homes.add(new Info(i, j));
                }
                if (s.equals("2")) {
                    stores.add(new Info(i, j));
                }
            }
        }
    }

    static int calc() {
        int sum = 0;

        for (Info home : homes) { // 각 집을 돌면서
            int dist = Integer.MAX_VALUE;

            for (int i = 0; i < M; i++) {
                Info store = stores.get(sel[i]);

                dist = Math.min(
                        dist,
                        Math.abs(home.x - store.x) + Math.abs(home.y - store.y)
                        );
            }
            sum += dist;
        }

        return sum;
    }

    static void rec_func(int depth) {
        if (depth == M) {
            // 최소값 계산
            ans = Math.min(ans, calc());
        } else {
            int start;
            if (depth == 0) {
                start = 0;
            } else {
                start = sel[depth - 1] + 1;
            }
            for (int i = start; i < stores.size(); i++) {
                sel[depth] = i;
                rec_func(depth + 1);
                sel[depth] = 0;
            }
        }
    }

    static void pro() {
        // 치킨집 M개를 골라 도시의 치킨 거리 계산 후 최소값 갱신
        sel = new int[M];

        rec_func(0);

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static class Info {
        int x;
        int y;

        Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
