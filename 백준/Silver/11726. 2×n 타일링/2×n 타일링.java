/*
    2 x N 크기의 직사각형을 1 x 2, 2 x 1 타일로 채우는 방법의 수를 구하는 문제
    입력 첫째 줄: N (1 <= N <= 1,000)
    출력 첫째 줄: 2 x N 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지
    
    가짜문제: Dy[i] = 2 x i 크기의 직사각형을 채우는 방법의 수
    진짜문제: Dy[N] = 2 x N 크기의 직사각형을 채우는 방법의 수
    초기값: Dy[1] = 1. Dy[2] = 2
    점화식: Dy[i] = Dy[i - 2] + Dy[i - 1]
        끝나는 타일이 1 x 2 타일 2개와, 2 x 1 타일일 때
    
*/

import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int MOD = 10007;
    static int N;
    static int[] Dy;
    
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }
    
    static void pro() {
        Dy = new int[1005];
        
        Dy[1] = 1;
        Dy[2] = 2;
        
        for (int i = 3; i <= 1000; i++) {
            Dy[i] = (Dy[i - 1] + Dy[i - 2]) % MOD;
        }
        
        System.out.println(Dy[N]);
        
    }
    
    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}

