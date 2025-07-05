import java.io.*;

// 시간 복잡도: 반복문 완탐 -> 최악의 경우 하루에 1씩만 오르고 V = 10억, -> 10억 -> 1초, 시간초과
// 수식 계산으로 풀면 O(1)
// https://www.acmicpc.net/problem/2869
class Main {

    static int A, B, V;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        A = Integer.parseInt(s[0]);
        B = Integer.parseInt(s[1]);
        V = Integer.parseInt(s[2]);
    }

    static void pro() {
        int ans = 1;
        if ((V - A) % (A - B) == 0) {
            ans += (V - A) / (A - B);
        } else {
            ans += ((V - A) / (A - B)) + 1;
        }
        System.out.println(ans);
    }
}
