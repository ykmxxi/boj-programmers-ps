// https://www.acmicpc.net/problem/2800

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static Set<String> rs;
    static String line;
    static Map<Integer, Integer> adj; // 괄호쌍
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();

        pro();
        rs.remove(line);
        for (String s : rs) {
            sb.append(s).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void input() throws IOException {
        line = br.readLine();

        adj = new HashMap<>();
        visited = new boolean[line.length()];
        rs = new TreeSet<>(); // 사전순 정렬
    }

    static void pro() {
        // 모든 괄호쌍을 저장하고 조합으로 제거 -> 10C1 + 10C2 + ... + 10C10 -> 1024개니까 시간 복잡도 가능
        findPair(); // O(1)

        dfs(0, line.length());
    }

    static void findPair() {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '(') {
                dq.push(i);
            } else if (ch == ')') {
                int x = dq.pop();
                adj.put(x, i);
                adj.put(i, x);
            }
        }
    }

    static void dfs(int idx, int len) {
        StringBuilder sb2 = new StringBuilder();
        if (idx == len) {
            for (int i = 0; i < line.length(); i++) {
                if (visited[i]) {
                    continue;
                }
                sb2.append(line.charAt(i));
            }
            rs.add(sb2.toString());
            return;
        }
        if (line.charAt(idx) == '(') { // 여는 괄호를 만나면
            visited[idx] = true;
            visited[adj.get(idx)] = true;

            dfs(idx + 1, len);

            visited[idx] = false;
            visited[adj.get(idx)] = false;
        }
        // 숫자거나 닫는 괄호(이미 visited 처리 댐)
        dfs(idx + 1, len);
    }
}
