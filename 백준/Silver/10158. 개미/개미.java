// https://www.acmicpc.net/problem/10158

// 시간 복잡도: 완탐 -> 2초, 사이클을 구해서 검색
// cycle 찾기? -> O(1) 가능

import java.io.*;

public class Main {

    static int w, h, p, q, t;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tk = br.readLine().split(" ");
        w = Integer.parseInt(tk[0]);
        h = Integer.parseInt(tk[1]);

        tk = br.readLine().split(" ");
        p = Integer.parseInt(tk[0]);
        q = Integer.parseInt(tk[1]);

        t = Integer.parseInt(br.readLine());
    }

    static void pro() {
        int curX = (p + t) % (w * 2); // 왼쪽 w + 오른쪽 w -> 2w
        int curY = (q + t) % (h * 2); // 위 h + 아래 h -> 2h

        // 범위 체크
        if (curX > w) {
            curX = w - (curX - w);
        }
        if (curY > h) {
            curY = h - (curY - h);
        }
        System.out.println(curX + " " + curY);
    }
}
