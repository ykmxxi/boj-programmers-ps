// 랜선 자르기: https://www.acmicpc.net/problem/1654
// 시간 복잡도: O(N logN) + O(logN) -> O((N+1) logN)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int K, N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[K];

        for (int i=0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        // N개의 랜선 필요, K개의 랜선 길이가 제각각 -> N개의 같은 길이의 랜선
        // 만들 수 있는 최대 랜선의 길이
        Arrays.sort(arr);

        long L = 1L;
        long R = Integer.MAX_VALUE;
        long ans = 0L;

        while (L <= R) {
            long mid = (L + R) / 2; // int -> overflow -> 3*Integer.MAX / 2

            if (check(mid)) { // N개를 만들 수 있다 -> 길이를 늘리자
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        System.out.println(ans);
    }

    static boolean check(long len) {
        long cnt = 0L;
        for (int num : arr) {
            cnt += num / len;
        }
        return cnt >= N;
    }

}
