import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, X;
	static MyStack<Integer> stack;

	static void pro(String command) {
		switch (command) {
			case "push":
				stack.push(X);
				break;
			case "pop":
				stack.pop();
				break;
			case "size":
				stack.size();
				break;
			case "empty":
				stack.empty();
				break;
			case "top":
				stack.top();
				break;
		}
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		stack = new MyStack<>(new ArrayList<>());

		while (N-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			String command = st.nextToken();
			while (st.hasMoreTokens()) {
				X = Integer.parseInt(st.nextToken());
			}

			pro(command);
		}
	}

	static class MyStack<Integer> {
		private final ArrayList<Integer> stack;

		public MyStack(ArrayList<Integer> stack) {
			this.stack = stack;
		}

		public void push(Integer x) {
			stack.add(x);
		}

		public void pop() {
			if (stack.isEmpty()) {
				System.out.println(-1);
			} else {
				System.out.println(stack.get(stack.size() - 1));
				stack.remove(stack.size() - 1);
			}
		}

		public void size() {
			System.out.println(stack.size());
		}

		public void empty() {
			if (stack.isEmpty()) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}

		public void top() {
			if (stack.isEmpty()) {
				System.out.println(-1);
			} else {
				System.out.println(stack.get(stack.size() - 1));
			}
		}
	}
}
