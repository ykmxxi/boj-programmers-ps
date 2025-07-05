import java.io.*;

// 시간 복잡도: O(1)
// https://www.acmicpc.net/problem/2851
class Main {

    static int size = 10;
    static int target = 100;
    static int[] M = new int[size];

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < size; i++) {
            M[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        int sum = M[0];
        for (int i = 1; i < size; i++) {
            if (Math.abs(target - sum) >= Math.abs(target - sum - M[i])) {
                sum += M[i];
                continue;
            }
            break;
        }
        System.out.println(sum);
    }
}
