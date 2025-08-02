// 숫자 카드 - https://www.acmicpc.net/problem/10815
// 시간 복잡도: O(N logN) + O(M logN)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M; // 1 <= N,M <= 500,000
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        input();
        pro();

        bw.flush();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
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

    static void pro() throws IOException {
        Arrays.sort(A); // 이분 탐색을 위한 정렬: O(N logN)

        for (int n : B) { // O(M logN)
            int L = 0;
            int R = A.length - 1;
            boolean flag = true;

            while (L <= R) {
                int mid = (L + R) / 2;
                if (A[mid]>n) { // n이 작으면
                    R = mid-1;
                } else if (A[mid]<n) { // n이 크면
                    L = mid+1;
                } else {
                    bw.write("1 ");
                    flag = false;
                    break;
                }
            }
            if (flag) {
                bw.write("0 ");
            }
        }
    }
}
