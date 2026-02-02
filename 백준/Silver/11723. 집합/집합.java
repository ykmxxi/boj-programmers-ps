import java.io.*;

// 시간 복잡도: O(M)
// 공간 복잡도: O(1)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int bit = 0;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            pro(br.readLine());
        }

        System.out.print(sb);
    }

    static void pro(String line) {
        // 공집합 S
        // add: S에 x 추가, (1 <= x <= 20), 이미 있는 경우 무시
        // remove: S에서 x 제거, 없으면 무시
        // check x: S에 x 존재하면 1, 없으면 0
        // toggle x: S에 x 있으면 제거, 없으면 x 추가
        // all: S를 {1,2,3,...,20}으로 변경
        // empty: S를 공집합으로
        String[] tokens = line.split(" ");
        switch (tokens[0]) {
            case "add": {
                int x = Integer.parseInt(tokens[1]);
                bit |= (1 << (x - 1)); // x번째 비트 켜기
                break;
            }
            case "remove": {
                int x = Integer.parseInt(tokens[1]);
                bit &= ~(1 << (x - 1)); // x번째 비트 끄기
                break;
            }
            case "check": {
                int x = Integer.parseInt(tokens[1]);
                if ((bit & (1 << (x - 1))) == (1 << (x - 1))) {
                    sb.append(1).append('\n');
                } else { // (bit & (1 << (x-1))) != (1 << (x-1))
                    sb.append(0).append('\n');
                }
                break;
            }
            case "toggle": {
                int x = Integer.parseInt(tokens[1]);
                bit ^= (1 << (x - 1));  // x번째 비트 토글
                break;
            }
            case "all": { // 모든 비트 켜기
                bit = (1 << 20) - 1;  // 0xFFFFF (20비트 모두 1)
                break;
            }
            case "empty": { // 모든 비트 끄기
                bit = 0;
                break;
            }
        }

    }
}
