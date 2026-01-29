import java.util.*;
import java.io.*;

// 시간 복잡도:
// 공간 복잡도:
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, head, tail;
    static int[] A = new int[10005];
    static String[] commands;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        commands = new String[N];
        for (int i= 0 ; i < N; i++) {
            commands[i] = br.readLine();
        }
    }

    static void pro() {
        for (String c : commands) {
            if (c.startsWith("push")) {
                push(Integer.parseInt(c.split(" ")[1]));
            } else if (c.equals("pop")) {
                sb.append(pop()).append('\n');
            } else if (c.equals("size")) {
                sb.append(size()).append('\n');
            } else if (c.equals("empty")) {
                sb.append(empty()).append('\n');
            } else if (c.equals("front")) {
                sb.append(front()).append('\n');
            } else {
                sb.append(back()).append('\n');
            }
        }
        System.out.print(sb);
    }

    static void push(int num) {
        A[tail++] = num;
    }

    static int pop() {
        if (size() == 0) {
            return -1;
        }
        int value = A[head];
        A[head] = 0;
        head++;
        return value;
    }

    static int size() {
        return tail - head;
    }

    static int empty() {
        if (tail == head) {
            return 1;
        }
        return 0;
    }

    static int front() {
        if (empty() == 1) {
            return -1;
        }
        return A[head];
    }

    static int back() {
        if (empty() == 1) {
            return -1;
        }
        return A[tail - 1];
    }
}
