/*
    - 숫자 카드 N개를 가지고 있을 때, M개의 숫자 카드가 주어지면 몇 개 가지고 있는지 세기
    - 1 <= N <= 500,000 (int)
    - 1 <= M <= 500,000 (int)
 */

import java.util.*;
import java.io.*;
public class Main {
    
    static StringTokenizer st;
    static int n; // 숫자 카드 개수
    static int m; // 찾을 카드 개수
    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        // 숫자 카드 입력
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        // 찾을 카드 입력
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(map.getOrDefault(num, 0) + " ");
        }

        System.out.println(sb);
    }
}
