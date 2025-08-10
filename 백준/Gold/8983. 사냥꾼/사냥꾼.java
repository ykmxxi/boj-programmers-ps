// 사냥꾼: https://www.acmicpc.net/problem/8983
// 시간 복잡도: O(N logM)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, L;
    static int[] gun;
    static Point[] A;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken()); // 사대의 수
        N = Integer.parseInt(st.nextToken()); // 동물의 수
        L = Integer.parseInt(st.nextToken()); // 사정거리

        gun = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            gun[i] = Integer.parseInt(st.nextToken());
        }

        A = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            A[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void pro() {
        // N 마리 동물 격자형 배열에 위치, 일직선 위치 M 개의 사대 사격
        // L 사정거리 -> Math.abs(사대x - 동물x) + 동물y <= L
        int ans = 0;
        Arrays.sort(gun);

        for (Point p : A) { // O(N) -> 동물마다 잡을 수 있는 사대를 확인
            if (check(p)) {
                ans++;
            }
        }
        System.out.println(ans);

    }

    static boolean check(Point p) {
        int left = 0;
        int right = M - 1;

        // p.x, p.y는 고정값
        while (left <= right) { // logN
            int mid = (right - left) / 2 + left;
            int distance = Math.abs(gun[mid] - p.x) + p.y;
            if (distance <= L) {
                return true;
            }
            if (gun[mid] < p.x) { // 동물보다 사대가 왼쪽이면 오른쪽으로 
                left = mid + 1;
            } else if (gun[mid] > p.x){
                right = mid - 1;
            } else { // 동물과 같은 x 좌표이면 최단 거리이기에 여기까지 도착하면 false
                return false;
            }
        }
        return false;
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
