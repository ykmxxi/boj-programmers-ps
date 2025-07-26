// https://www.acmicpc.net/problem/2531
// 시간 복잡도: O(Nk - k^2) 최악의 경우 (N - k)k번 탐색 -> 슬라이딩 윈도우
// 투 포인터로 푸는게 젤 빠를듯 -> O(N - k)
// 회전인걸 생각안함 -> L이 N-1 까지 갈 수 있음

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] arr, used;
    static int N, d, k, c;
    static int max = 0; // max는 d를 넘을 수 없 -> max == d 종료

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[N];
        used = new int[d + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        // 임의의 위치부터 k개 연속 먹을 경우 할인
        // 쿠폰에 적힌 초밥 하나를 추가 무료 제공, 벨트위에 없으면 추가
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            if (used[arr[i]] == 0) {
                cnt++;
            }
            used[arr[i]]++;
        }
        if (used[c] == 0) {
            max = cnt + 1;
        } else {
            max = cnt;
        }

        int L = 1;
        while (L < N) {
            int R = (L + k - 1) % N;
            if (max == d) {
                break;
            }
            if (used[arr[L - 1]] == 1) {
                cnt--;
            }
            used[arr[L - 1]]--;
            if (used[arr[R]] == 0) {
                cnt++;
            }
            used[arr[R]]++;

            int b = used[c] == 0 ? 1 : 0;
            max = Math.max(cnt + b, max);
            L++;
        }
        System.out.println(max);
    }
}
