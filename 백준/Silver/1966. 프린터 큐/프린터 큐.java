/*
    - 현재 큐의 가장 앞에 있는 문서의 중요도 확인
    - 현재 문서보다 중요도가 높은 문서가 뒤에 있으면 큐의 가장 뒤에 재배치

 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static LinkedList<Info> q;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        q = new LinkedList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            q.offer(new Info(i, Integer.parseInt(st.nextToken())));
        }
    }

    static void pro() {
        int cnt = 0;

        while (!q.isEmpty()) {
            boolean canPrint = true;
            Info cur = q.poll(); // 현재 문서를 빼내서

            for (int i = 0; i < q.size(); i++) {
                if (cur.priority < q.get(i).priority) { // 현재 문서보다 중요도가 높은게 존재하면
                    q.offerLast(cur); // 현재 정보를 뒤에 넣고
                    canPrint = false;
                    break;
                }
            }
            if (canPrint) {
                cnt++;
                if (cur.idx == M) {
                    break;
                }
            }

        }

        sb.append(cnt).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            input();
            pro();
        }

        System.out.print(sb);
    }

    static class Info {
        int idx;
        int priority;

        Info(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }
}
