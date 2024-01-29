/*

- 1 부터 10억 (int) 양의 정수: 산성 용액의 특성값
- -1 부터 -10억 (int) 음의 정수: 알칼리성 용액의 특성값
- 같은 양의 두 용액 혼합 -> 각 용액의 특성값의 합
- 특성값이 0에 가장 가까운 용액을 만들려고 한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N; // 용액의 수 2 ~ 100,000
    static int[] arr;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        Arrays.sort(arr);
        int best = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;
        int L = 0;
        int R = N - 1;

        while (L < R) {
            int sum = arr[L] + arr[R];

            if (Math.abs(sum) < best) {
                best = Math.abs(sum);
                v1 = arr[L];
                v2 = arr[R];
            }

            if (sum > 0) {
                R--;
            } else {
                L++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(v1).append(' ').append(v2);
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
