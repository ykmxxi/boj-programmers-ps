// 블록 쌓기: https://www.acmicpc.net/problem/9998
// 시간 복잡도: O(N log C), C는 10^12

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static long[] Y, D;
    static long ans = 0L;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        Y = new long[N];
        D = new long[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            Y[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            D[i] = Long.parseLong(st.nextToken());
        }
    }

    static void pro() {
        // 너비 N의 블록 건물
        // 윤형 k 번째 열에 Y개의 블록을 쌓음, 동형 k 번째 열에 D개의 블록을 쌓음
        // 왼 -> 오, 블록의 개수가 감소하다 증가하는 꼴이 되어여 한다
        // 인접한 두 열의 블록의 개수는 1개씩만 차이, 가장 적은 열은 정중앙
        // 이분 탐색으로 중앙 개수 선택 -> 왼쪽 오른쪽 개수
        bs();

        System.out.println(ans);
    }

    static void bs() {
        long L = 0L;
        long R = 1_000_000_000_000L - (N / 2); // 1개씩은 차이나야 하기 때문에 최대값 - N/2 개해야 0, N-1 인덱스에서 N개
        long lc = count(L);
        long rc = count(R);

        while (L < R) {
            long mid = (R - L) / 2 + L;

            if (lc < rc) { // 가운데 블록의 수를 줄여
                R = mid;
                rc = count(R);
            } else { // 가운데 블록 수를 늘리기
                L = mid + 1;
                lc = count(L);
            }
        }
        ans = lc;
    }

    static long count(long start) {
        long total = 0L;
        long height = start;
        // 왼쪽 구간 count
        for (int i = N / 2; i >= 0; i--) {
            total += Math.abs(Y[i] - height);
            total += Math.abs(D[i] - height);
            height++;
        }
        // 오른쪽 구간 count
        height = start + 1;
        for (int i = N / 2 + 1; i < N; i++) {
            total += Math.abs(Y[i] - height);
            total += Math.abs(D[i] - height);
            height++;
        }
        return total;
    }
}
