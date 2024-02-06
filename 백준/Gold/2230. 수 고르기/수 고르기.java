/*
    - N개 정수로 이루어진 수열 A[1] ~ A[N], 두 수를 골랐을 때 차이가 M 이상이면서 제일 작은 경우 구하기
    -
 */
import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] A;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        Arrays.sort(A);
        int ans = Integer.MAX_VALUE;
        int R = 0;

        for (int L = 0; L < N - 1; L++) {
            while (R + 1 < N && A[R] - A[L] < M) {
                R++;
            }

            if (A[R] - A[L] >= M) {
                ans = Math.min(ans, A[R] - A[L]);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
