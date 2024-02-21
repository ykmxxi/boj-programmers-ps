/*
    - 노래마다 랭킹 리스트 존재, 매번 게임할 떄 마다 얻는 점수가 비오름차순(내림차순)으로 저장
    - [100, 90, 90, 80] - [1, 2, 2, 4]등
    - 랭킹에 오라갈 수 있는 점수가 P개일 때 새로운 점수가 랭킹에 올라가면 몇 등인지 구하기, 못올라가면 -1 출력
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, S, P;
    static List<Integer> rank;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        if (N == 0) {
            return;
        }

        st = new StringTokenizer(br.readLine(), " ");
        rank = new ArrayList<>();
        while (st.hasMoreTokens()) {
            rank.add(Integer.parseInt(st.nextToken()));
        }
    }

    static void pro() {
        int ans = 1;
        if (N == 0) {
            System.out.println(1);
            return;
        }
        if (rank.size() == P && S <= rank.get(rank.size() - 1)) {
            System.out.println(-1);
            return;
        }
        for (int sc : rank) {
            if (S < sc) {
                ans++;
            } else {
                break;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
