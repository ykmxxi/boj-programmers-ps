// 입국심사: https://www.acmicpc.net/problem/3079
// 시간 복잡도: O(log N)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M; // M명이 N개의 심사대에서 Tk 시간이 걸림
    static int[] arr;


    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        // 가장 앞의 사람은 비어있는 심사대로 가서 심사
        // 최악의 경우 N = 1, M = 10^9, T = 10^9 -> 10^18 -> long
        Arrays.sort(arr);
        long L = 1L;
        long R = (long) arr[N - 1] * M;
        long ans = 0L;
        // 매개변수 탐색 -> mid 시간으로 모두 검사 가능? -> yes or no
        while (L <= R) {
            long mid = (R - L) / 2 + L;
            if (check(mid)) { // mid 시간으로 심사 가능
                ans = mid;
                R = mid - 1L;
            } else { // mid 시간으로 심사 불가능
                L = mid + 1L;
            }
        }

        System.out.println(ans);
    }

    static boolean check(long time) {
        long cnt = 0L;
        for (int tk : arr) {
            cnt += time / tk;
            if (cnt >= M) {
                return true;
            }
        }
        return false;
    }
}
