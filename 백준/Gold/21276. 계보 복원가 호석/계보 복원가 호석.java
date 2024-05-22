import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static String[] names;
	static ArrayList<String> roots;
	static ArrayList<Integer>[] con;
	static ArrayList<String>[] child;
	static Map<String, Integer> map_name; // key: 부모, values: 직계 자식들

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		names = new String[N + 1];
		map_name = new HashMap<>();
		con = new ArrayList[N + 1];
		child = new ArrayList[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			names[i] = st.nextToken();
			map_name.put(names[i], i);

			con[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
		}

		M = Integer.parseInt(br.readLine());

		// X의 조상 중 Y가 존재
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String x = st.nextToken();
			String y = st.nextToken();

			con[map_name.get(x)].add(map_name.get(y));
		}
	}

	static void pro() {
		// Root 찾기
		roots = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (con[i].size() == 0) {
				roots.add(names[i]);
			}

			// 자신보다 depth 가 1이 적은 조상은 부모 노드
			for (int j : con[i]) {
				int depth_i = con[i].size();
				int depth_j = con[j].size();
				if (depth_i - 1 == depth_j) {
					child[j].add(names[i]);
				}
			}
		}

		// 출력
		sb.append(roots.size()).append('\n');

		Collections.sort(roots);
		for (String root : roots) {
			sb.append(root).append(' ');
		}
		sb.append('\n');

		Arrays.sort(names, 1, N + 1);
		for (int i = 1; i <= N; i++) {
			String name = names[i];
			int idx = map_name.get(name);
			sb.append(name).append(' ').append(child[idx].size());

			Collections.sort(child[idx]);
			for (String child : child[idx]) {
				sb.append(' ').append(child);
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
