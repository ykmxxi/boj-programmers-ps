// https://www.acmicpc.net/problem/6603
// 시간 복잡도: O(N! / M!(N - M)!) (N개 중 M개를 선택)

// 독일 로또: 49 C 6 -> N개 중 M개를 중복없이 순서 있게 나열 -> 순열(재귀) -> K개 중 6개를 고르는 중복 없는 조합
// k = 12, 최악의 경우 O (12! / (6! * 6!)) -> 13 * 12 * ... * 8

import java.io.*;
import java.util.*;

public class Main {

    static String line;
    static int K;
    static int[] arr, selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            line = br.readLine();
            if (line.equals("0")) {
                break;
            }

            input();
            pro();
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void input() {
        String[] st = line.split(" ");
        K = Integer.parseInt(st[0]);
        arr = new int[K];
        selected = new int[6];
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st[i + 1]);
        }
    }

    static void pro() {
        rec(0);
    }

    static void rec(int depth) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(arr[selected[i]]).append(' ');
            }
            sb.append("\n");
        } else {
            int start;
            if (depth == 0) {
                start = 0;
            } else {
                start = selected[depth - 1] + 1;
            }
            for (int i = start; i < K; i++) {
                selected[depth] = i; // arr의 몇 번째 idx가 선택되었는지 기록
                rec(depth + 1);
                selected[depth] = 0; // 초기화
            }
        }
    }
}
