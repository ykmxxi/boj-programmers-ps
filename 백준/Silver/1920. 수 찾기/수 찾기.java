/*
- N개의 정수 A[1] ~ A[N] 이 안에 X 라는 정수가 존재하는지 알아내기
- 1 <= N <= 100,000
- 1 <= M <= 100,000
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] A, B;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        B = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void binarySearch(int target) {
        int L = 0;
        int R = A.length - 1;
        int answer = 0;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (A[mid] < target) {
                L = mid + 1;
            } else if (A[mid] > target) {
                R = mid - 1;
            } else {
                answer = 1;
                break;
            }
        }

        sb.append(answer).append('\n');
    }

    static void pro() {
        Arrays.sort(A);

        for (int i = 0; i < M; i++) {
            binarySearch(B[i]);
        }

        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
