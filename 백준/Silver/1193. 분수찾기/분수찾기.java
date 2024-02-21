/*
    - X번째 분수 규칙
    - 홀수 1 + 2 + 3 + 4 -> 홀수(우상향 이동), 짝수(좌하향 이동)
 */

import java.util.*;
import java.io.*;

public class Main {

    static int X;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());
    }

    static void pro() {
        int prevSum = 0, crossCnt = 1;

        while (true) {
            if (X <= prevSum + crossCnt) {
                if (crossCnt % 2 == 1) {// 대각선 개수가 홀수
                    System.out.println((crossCnt - (X - prevSum - 1)) + "/" + (X - prevSum));
                } else { // 대각선 개수가 짝수
                    System.out.println((X - prevSum) + "/" + (crossCnt - (X - prevSum - 1)));
                }
                break;
            } else {
                prevSum += crossCnt;
                crossCnt++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
