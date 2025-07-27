// https://www.acmicpc.net/problem/1074
// 시간 복잡도: O(N)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, r, c, value;

    public static void main(String[] args) throws IOException {
        input();

        // 1,2,3,4 -> 1,2,3,4 형식으로 속한 사분면 찾기
        rec((int) Math.pow(2, N), 0, 0);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    static void rec(int len, int x, int y) {
        if (len == 1) {
            System.out.println(value);
        } else {
            int nl = len / 2;
            if (r < x + nl && c < y + nl) { // 1사분면
                rec(nl, x, y);
            } else if (r < x + nl && c >= y + nl) { // 2사분면
                value += nl * nl;
                rec(nl, x, y + nl);
            } else if (r >= x + nl && c < y + nl) { // 3사분면
                value += (nl * nl) * 2;
                rec(nl, x + nl, y);
            } else { // 4사분면
                value += (nl * nl) * 3;
                rec(nl, x + nl, y + nl);
            }
        }
    }
}

