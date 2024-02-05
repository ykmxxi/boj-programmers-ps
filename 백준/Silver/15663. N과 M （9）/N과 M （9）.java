import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int M;
    static int[] numbers;
    static int[] selected;
    static int[] used;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        numbers = new int[N + 1];
        selected = new int[M + 1];
        used = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            numbers[i] = scan.nextInt();
        }
    }

    static void rec_func(int k) {
        if (k == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else {
            int last = 0; // 마지막에 사용한 숫자 저장
            for (int candidate = 1; candidate <= N; candidate++) {
                if (used[candidate] == 1) {
                    continue;
                }
                if (numbers[candidate] == last) {
                    continue;
                }
                last = numbers[candidate];
                selected[k] = numbers[candidate];
                used[candidate] = 1;

                rec_func(k + 1);

                selected[k] = 0;
                used[candidate] = 0;
            }
        }
    }

    public static void main(String[] args) {
        input();
        Arrays.sort(numbers, 1, N + 1);
        rec_func(1);
        System.out.println(sb.toString());
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}
