/*

    - 블루레이에 총 N개의 강의가 존재, 녹화할 때 강의의 순서가 바뀌면 안 된다
    - 블루레이의 개수를 줄이려고 한다(최소값) -> 이분 탐색
    - M개의 블루레이에 모든 강의를 녹화, 이때 블루레이의 크기를 최소화 하려고한다(최소값) -> 이분 탐색
    - 단 M개의 블루레이 모두 같은 크기
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N; // 1 이상 10만 이하
    static int M; // 1 이상 N 이하
    static int max = 0;
    static int[] A;

    static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, A[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean can(int min) {
        int cnt = 1;
        int sum = 0;
        for (int a : A) {
            if (sum + a > min) {
                cnt++;
                sum = a;
            } else {
                sum += a;
            }
        }

        return cnt <= M;
    }

    static void pro() {
        // 한 개의 강의를 담으려면 적어도 가장 긴 강의 시간보다 큰 블루레이를 가지고 있어야 한다
        // M = 1 이면 모든 강의를 한 개의 블루레이에 담을 수 있어야 한다 -> 100,000개의 강의가 모두 10,000 분이면 10^9 분의 블루레이 1개가 필요
        int L = max, R = 1000000000; 
        int ans = 0;

        while (L <= R) {
            int mid = (L + R) / 2;

            if (can(mid)) { // mid 분 블루레이 M개로 모두 담을 수 없으면
                ans = mid;
                R = mid - 1;
            } else { // mid 분 블루레이 M개로 모두 담을 수 있으면
                L = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
