// 개똥벌레: https://www.acmicpc.net/problem/3020
// 시간 복잡도: O(logN * NH) -> 시간 초과 -> O(H * logN)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, H;
    static int[] bottom, top;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        bottom = new int[N/2]; // 석순(홀수, 아래 -> 위)
        top = new int[N/2]; // 종유석(짝수, 위 -> 아래)
        for (int i = 0; i < N/2; i++) {
            int b = Integer.parseInt(br.readLine());
            int t = Integer.parseInt(br.readLine());
            bottom[i] = b;
            top[i] = t;
        }
    }

    static void pro() {
        // 길이 N(짝수), 높이 H
        // 첫 장애물은 석순(아래 -> 위) 종유석(위 -> 아래) 선순 번갈아 가면서 나옴
        Arrays.sort(bottom);
        Arrays.sort(top);

        int cnt1 = Integer.MAX_VALUE; // 부서진 장애물 개수
        int cnt2 = 0; // 구간 수

        for (int i = 1; i <= H; i++) {
            int cnt = calc(i, bottom) + calc(H - i + 1, top); // 석순, 종유석 부서지는 것 계산
            if (cnt == cnt1) {
                cnt2++;
            } else if (cnt1 > cnt) {
                cnt1 = cnt;
                cnt2 = 1;
            }
        }

        sb.append(cnt1).append(" ").append(cnt2);
        System.out.println(sb);
    }

    static int calc(int section, int[] arr) { // 구간의 위치를 받아 계산
        int L = 0;
        int R = N / 2;

        while (L < R) {
            int mid = (L + R) / 2;
            if (arr[mid] >= section) { // 가운데 존재하는 것이 더 크면
                R = mid;
            } else {
                L = mid + 1;
            }
        }
        return arr.length - R; // 부서지는 개수
    }
}
