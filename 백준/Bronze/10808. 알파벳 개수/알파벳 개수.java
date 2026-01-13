import java.io.*;

// 시간 복잡도: O(N)
// 공간 복잡도: O(1)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] A = new int[26];

    static String str;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        str = br.readLine();
    }

    static void pro() {
        for (char ch : str.toCharArray()) {
            A[ch - 'a']++;
        }

        for (int num : A) {
            sb.append(num).append(' ');
        }
        System.out.print(sb);
    }
}
