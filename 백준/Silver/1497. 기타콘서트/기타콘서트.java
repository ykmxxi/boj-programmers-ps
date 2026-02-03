import java.io.*;
import java.util.StringTokenizer;

// 시간 복잡도: O(2^N * N), max 10240 -> O(1)
// 공간 복잡도: O(N), O(1)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, M; // N; 1 ~ 10, M: 1 ~ 50
    static long[] arr;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            st.nextToken();

            for (char ch : st.nextToken().toCharArray()) {
                arr[i] = (arr[i] << 1) | (ch == 'Y' ? 1 : 0);
            }
        }
    }

    static void pro() {
        int maxSong = -1;
        int ans = N;

        for (int i = 1; i < (1 << N); i++) {
            long comb = 0L;
            int cnt = 0;

            for (int j = 0; j < N; j++) {
                if ((i & (1L << j)) != 0) { // j 번째 기타 on
                    comb |= arr[j];
                    cnt++;
                }
            }

            int songCnt = Long.bitCount(comb);
            if (songCnt > maxSong) {
                maxSong = songCnt;
                ans = cnt;
            } else if (songCnt == maxSong && cnt < ans) {
                ans = cnt;
            }
        }
        
        if (maxSong == 0) {
            ans = -1;
        }

        System.out.print(ans);
    }
}
