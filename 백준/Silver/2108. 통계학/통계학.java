/*
    - 현재 큐의 가장 앞에 있는 문서의 중요도 확인
    - 현재 문서보다 중요도가 높은 문서가 뒤에 있으면 큐의 가장 뒤에 재배치

 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N, max = - 5000, min = 5000;
    static int[] A, cnt;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        cnt = new int[8005];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, A[i]);
            min = Math.min(min, A[i]);
            cnt[A[i] + 4000]++;
        }
    }

    static void pro() {
       StringBuilder sb = new StringBuilder();

       // 산술 평균
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += A[i];
        }
        sb.append(Math.round((double)sum / N)).append('\n');

        // 중앙값
        Arrays.sort(A);
        sb.append(A[N / 2]).append('\n');

        // 최빈값
        int curCnt = 0;
        int maxCnt = -1;
        for (int i = min + 4000; i <= max + 4000; i++) {
            if (maxCnt < cnt[i]) {
                curCnt = 1;
                maxCnt = cnt[i];
                continue;
            }
            if (maxCnt == cnt[i]) {
                curCnt++;
            }
        }

        if (curCnt >= 2) {
            int sec = 0;
            for (int i = min + 4000; i <= max + 4000; i++) {
                if (sec == 1 && maxCnt == cnt[i]) {
                    sb.append(i - 4000).append('\n');
                    break;
                }
                if (maxCnt == cnt[i]) {
                    sec++;
                }
            }
        } else {
            for (int i = min + 4000; i <= max + 4000; i++) {
                if (maxCnt == cnt[i]) {
                    sb.append(i - 4000).append('\n');
                }
            }
        }

        // 범위
        sb.append(max - min);

        System.out.print(sb);

    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
