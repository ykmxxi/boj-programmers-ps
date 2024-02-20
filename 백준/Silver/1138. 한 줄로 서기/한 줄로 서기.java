/*
    - 자기보단 큰 사람이 왼쪽에 몇 명 있는지만 기억
    - 사람들의 기억만 주어질 때, 줄을 어떻게 서야 하는지 출력
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] A;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        List<Integer> ans = new ArrayList<>();

        for (int i = N; i >= 1; i--) {
            ans.add(A[i], i);
        }
        StringBuilder sb = new StringBuilder();
        for (int h : ans) {
            sb.append(h).append(' ');
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
