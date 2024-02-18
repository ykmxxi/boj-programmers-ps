import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static Info[] A;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        A = new Info[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            A[i] = new Info(w, h);
        }
    }

    static void pro() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int rank = 1;

            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }

                if (A[i].w < A[j].w && A[i].h < A[j].h) {
                    rank++;
                }
            }

            sb.append(rank).append(' ');
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static class Info {
        int w;
        int h;

        Info(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }
}
