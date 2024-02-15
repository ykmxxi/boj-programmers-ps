import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static ArrayList<Integer> preorder;

	static void input() throws IOException {
		String input = " ";
		preorder = new ArrayList<>();

		while ((input = br.readLine()) != null) {
			preorder.add(Integer.parseInt(input));
		}
	}

	static void traverse(int l, int r) {
		if (l > r) {
			return;
		}

		int mid = r;  // 왼쪽과 오른쪽 subtree를 가르는 기준 위치를 나타내는 변수
		for (int i = l + 1; i <= r; i++) {
			if (preorder.get(i) > preorder.get(l)) {
				mid = i - 1;
				break;
			}
		}

		traverse(l + 1, mid);
		traverse(mid + 1, r);
		sb.append(preorder.get(l)).append('\n');
	}

	static void pro() {
		traverse(0, preorder.size() - 1);
		System.out.print(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
