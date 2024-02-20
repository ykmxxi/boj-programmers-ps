/*
    - 8x8 체스판에 킹이 존재, 알파벳(열), 숫자(행)
    - 가장 왼쪽 열: A, 가장 오른쪽 열 H -> A1(왼쪽 아래 코너), B1(A1 오른쪽 칸)
 */
import java.util.*;
import java.io.*;

public class Main {

    static String king, rock;
    static int N;
    static String[] order;
    static Map<String, int[]> dir = new HashMap<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        king = st.nextToken();
        rock = st.nextToken();
        N = Integer.parseInt(st.nextToken());

        order = new String[N];
        for (int i = 0; i < N; i++) {
            order[i] = br.readLine();
        }

        dir.put("R", new int[]{0, 1});
        dir.put("L", new int[]{0, -1});
        dir.put("B", new int[]{-1, 0});
        dir.put("T", new int[]{1, 0});
        dir.put("RT", new int[]{1, 1});
        dir.put("LT", new int[]{1, -1});
        dir.put("RB", new int[]{-1, 1});
        dir.put("LB", new int[]{-1, -1});
    }

    static boolean check(int x, int y) {
        int rx = rock.charAt(1) - '0';
        int ry = rock.charAt(0) - 'A' + 1;
        return x == rx && y == ry;
    }

    static void move(String o) {
        int kx = king.charAt(1) - '0';
        int ky = king.charAt(0) - 'A' + 1;

        int[] a = dir.get(o);
        int nx = kx + a[0];
        int ny = ky + a[1];


        if (nx >= 1 && ny >= 1 && nx <= 8 && ny <= 8) { // 범위 내에서
            if (check(nx, ny)) { // 움직일 위치에 돌이 있으면 돌을 욺기기
                int rx = rock.charAt(1) - '0' + a[0];
                int ry = rock.charAt(0) - 'A' + 1 + a[1];

                if (rx >= 1 && ry >= 1 && rx <= 8 && ry <= 8) { // 돌을 옮길 수 있으면
                    StringBuilder sb = new StringBuilder();
                    rock = sb.append((char)(ry - 1 + 'A')).append(rx).toString();
                    sb = new StringBuilder();
                    king = sb.append((char)(ny - 1 + 'A')).append(nx).toString();
                }
            } else { // 움직일 위치에 돌이 없으면 킹만 옮기기
                StringBuilder sb = new StringBuilder();
                king = sb.append((char)(ny - 1 + 'A')).append(nx).toString();
            }
        }
    }

    static void pro() {
        for (String s : order) {
            move(s);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(king).append('\n').append(rock).append('\n');
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
