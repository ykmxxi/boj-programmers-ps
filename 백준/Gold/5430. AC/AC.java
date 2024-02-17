/*
    - AC: 정수 배열에 연산, R(뒤집기), D(버리기)
    - R: 배열에 있는 수의 순서를 뒤집기, D: 첫 번째 수를 버리는 함수(비어있는 경우 에러 발생)
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String order;
    static int N;
    static Deque<Integer> dq;
    static boolean err;

    static void input() throws IOException {
        order = br.readLine();

        N = Integer.parseInt(br.readLine());

        dq = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine().replaceAll("\\[|\\]", ""), ",");
        for (int i = 0; i < N; i++) {
            dq.offer(Integer.parseInt(st.nextToken()));
        }
    }

    static void delete(boolean isReverse) {
        if (dq.size() == 0) {
            err = true;
            return;
        }
        if (isReverse) {
            dq.removeLast();
        } else {
            dq.removeFirst();
        }
    }

    static void pro() {
        boolean r = false;
        err = false;

        for (int i = 0; i < order.length(); i++) {
            if (order.charAt(i) == 'R') {
                r = !r;
            } else {
                delete(r);
            }
        }

        if (err) {
            sb.append("error").append('\n');
        } else {
            sb.append('[');
            if (r) {
                while (!dq.isEmpty()) {
                    if (dq.size() == 1) {
                        sb.append(dq.pollLast());
                    } else {
                        sb.append(dq.pollLast()).append(',');
                    }
                }
            } else {
                while (!dq.isEmpty()) {
                    if (dq.size() == 1) {
                        sb.append(dq.pollFirst());
                    } else {
                        sb.append(dq.pollFirst()).append(',');
                    }
                }
            }
            sb.append(']').append('\n');
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            input();
            pro();
        }

        System.out.print(sb);
    }
}
