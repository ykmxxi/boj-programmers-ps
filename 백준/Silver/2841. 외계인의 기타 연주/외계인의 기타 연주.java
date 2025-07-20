// https://www.acmicpc.net/problem/2841
// 시간 복잡도:

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, P, ans; // 500,000 / 300,000
    static List<Integer>[] arr; // 줄, 프렛
    static Deque<Integer>[] dq;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N];
        dq = new ArrayDeque[7];
        for (int i = 1; i <= 6; i++) {
            dq[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i] = new ArrayList<>();
            arr[i].add(Integer.parseInt(st.nextToken()));
            arr[i].add(Integer.parseInt(st.nextToken()));
        }
    }

    static void pro() {
        // 기타 1 ~ 6, 각 줄은 1 ~ P 프렛
        ans = 0;
        for (int i = 0; i < N; i++) {
            int line = arr[i].get(0);
            int pr = arr[i].get(1);
            while (!dq[line].isEmpty() && dq[line].peek() > pr) {
                dq[line].pop();
                ans++;
            }
            if (dq[line].isEmpty() || (!dq[line].isEmpty() && dq[line].peek() < pr)) {
                dq[line].push(pr);
                ans++;
            }
        }
        System.out.println(ans);
    }
}
