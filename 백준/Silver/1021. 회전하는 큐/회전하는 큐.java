import java.util.*;
import java.io.*;

// 시간 복잡도: O(N * M)
// 공간 복잡도: O(N)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] A;
    static LinkedList<Integer> dq = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        // N개, 양방향 순환 큐
        // 1: 첫 번째 원소 뽑아내기 (a1, ..., ak -> a2, ..., ak)
        // 2: 왼쪽 한 칸 이동 (a1, ..., ak -> a2, ..., ak, a1)
        // 3: 오른쪽 한 칸 이동 (a1, ..., ak -> ak, a1, ..., ak-1)
        for (int i = 1; i <= N; i++) {
            dq.offer(i);
        }

        int ans = 0;
        for (int i = 0; i < M; i++) {
            int targetIndex = getIndex(A[i]);
            int size = dq.size();
            int half;
            if (size % 2 == 0) {
                half = size / 2 - 1;
            } else {
                half = size / 2;
            }
            if (half >= targetIndex) {
                for (int j = 0; j < targetIndex; j++) {
                    dq.addLast(dq.poll());
                    ans++;
                }
            } else {
                for (int j = 0; j < size - targetIndex; j++) {
                    dq.addFirst(dq.pollLast());
                    ans++;
                }
            }
            dq.poll();
        }
        System.out.print(ans);
    }

    static int getIndex(int target) {
        int idx = 0;
        for (int num : dq) {
            if (num == target) {
                break;
            }
            idx++;
        }
        return idx;
    }
}
