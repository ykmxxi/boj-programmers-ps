import java.util.*;
import java.io.*;

class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int T, N, M;
    static int[] A, B;
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            input();
            pro();
        }
        
        System.out.print(sb);
    }
    
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        A = new int[N];
        B = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void pro() {
        int ans = 0;
        
        Arrays.sort(B);
        for (int i = 0; i < N; i++) {
            int L = 0;
            int R = M - 1;
            
            while (L <= R) {
                int mid = (L + R) / 2;
                if (B[mid] < A[i]) {
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            
            ans += L;
        }
        sb.append(ans).append('\n');
    }
}
