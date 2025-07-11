// 시간 복잡도: O(n)
// 한 번에 한 개의 원판만 이동 -> 원판은 항상 위의 것이 아래의 것보다 작아야 한다
// 이동 횟수는 최소

import java.io.*;

public class Main {

    static int N, cnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        move(N, 1, 3); // N 개의 원판을 1 -> 3 이동

        System.out.print(cnt + "\n" + sb);
    }

    static void move(int m, int from, int to) {
        if (m > 1) { // 옮겨야할 원판이 남아있다면
            // 먼저 시작 지점에서 가장 아래 원판을 제외하고, 시작과 목표를 제외한 나머지 기둥으로 옮긴다
            move(m - 1, from, 6 - from - to);
        }

        cnt++;
        sb.append(from).append(' ').append(to).append('\n');

        if (m > 1) {
            // 나머지 기둥에 존재하는 그룹을 to(목적지)로 옮긴다
            move(m - 1, 6 - from - to, to);
        }
    }
}
