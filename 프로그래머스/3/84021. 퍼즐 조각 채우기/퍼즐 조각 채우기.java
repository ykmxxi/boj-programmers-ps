/*
    격자 배열 -> 그래프 문제?
    퍼즐 조각을 보드의 빈칸에 채우는 규칙 (빈 칸: 0, 채워져 있는 곳: 1)
    	보드의 빈칸을 찾을 때는 0, 테이블의 퍼즐을 찾을 때는 1
    - 조각은 한 번에 하나씩 채워 넣는다
    - 조각을 회전시킬 수 있다
    - 조각을 뒤집을 수 없다
    - 새로 채워 넣은 퍼즐 조각과 인접한 칸이 비어있으면 안 된다. (상하좌우를 모두 빈 칸이 아니어야 함)
    	빈 칸에 퍼즐을 놓는 순간 인접한 칸이 비어있으면 안됨
    규칙에 맞게 최대한 많은 퍼즐 조각을 채워 넣을 경우, 총 몇칸을 채울 수 있는지 return
*/

import java.util.*;

class Solution {
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int len;
	static int[][] visit;
	static ArrayList<ArrayList<Info>> blank, puzzle;

	static void bfs(int x, int y, int[][] arr, int type) {
		Queue<Info> q = new LinkedList<>();
		ArrayList<Info> tmp = new ArrayList<>();

		q.add(new Info(x, y));
		visit[x][y] = 1;
		tmp.add(new Info(0, 0)); // 빈 공간의 모양과 퍼즐의 모양 정보를 (0, 0) 기준으로 설정

		while (!q.isEmpty()) {
			Info info = q.poll();

			for (int k = 0; k < 4; k++) {
				int nx = info.x + dir[k][0];
				int ny = info.y + dir[k][1];

				if (nx >= 0 && ny >= 0 && nx < len && ny < len) { // 범위 내에서
					if (visit[nx][ny] == 0 && arr[nx][ny] == type) {
						q.add(new Info(nx, ny));
						visit[nx][ny] = 1;
						tmp.add(new Info(nx - x, ny - y)); // (0, 0) 기준이기 때문에 x, y를 빼주어야 함
					}
				}
			}
		}

		// 빈 공간과 퍼즐의 정보를 오름차순 정렬해야 함. 정렬되지 않으면 좌표가 뒤섞여 이상한 모양이 될 수 있음
		Collections.sort(tmp);
		if (type == 1) {
			puzzle.add(tmp);
		} else {
			blank.add(tmp);
		}
	}

	static boolean check(ArrayList<Info> empty, ArrayList<Info> puzzle) {
		// 90도씩 회전 시키기
		for (int i = 0; i < 4; i++) {
			int sx = puzzle.get(0).x;
			int sy = puzzle.get(0).y;

			// 회전시키면서 좌표가 달라지기 때문에 다시 (0, 0)을 기준으로 블록 좌표를 변경
			for (Info info : puzzle) {
				info.x -= sx;
				info.y -= sy;
			}

			boolean isCollect = true;

			for (int j = 0; j < empty.size(); j++) {
				Info emptyPoint = empty.get(j);
				Info blockPoint = puzzle.get(j);

				// 블록 좌표가 빈 공간의 좌표와 하나라도 다르면 중단
				if (emptyPoint.x != blockPoint.x || emptyPoint.y != blockPoint.y) {
					isCollect = false;
					break;
				}
			}

			if (isCollect) {
				return true;
			}
			else {
				// 90도 회전 : (x, y) -> (y, -x)
				for (Info info : puzzle) {
					int temp = info.x;

					info.x = info.y;
					info.y = -temp;
				}

				Collections.sort(puzzle);
			}

		}

		return false;
	}

	public int solution(int[][] game_board, int[][] table) {
		int answer = 0;

		len = game_board.length;
		blank = new ArrayList<>();
		puzzle = new ArrayList<>();

		// 퍼즐 조각 정보 저장
		visit = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (table[i][j] == 1 && visit[i][j] == 0) {
					bfs(i, j, table, 1);
				}
			}
		}

		// 초기화 후 게임판의 빈 공간 정보 저장
		visit = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (game_board[i][j] == 0 && visit[i][j] == 0) {
					bfs(i, j, game_board, 0);
				}
			}
		}

		// 저장한 보드의 빈 공간 정보와 퍼즐 모양 정보를 순회하면서 빈 공간에 퍼즐을 채워나감
		int[] filled = new int[blank.size()]; // 공간의 상태: 퍼즐 조각이 맞아 채워지면 1

		for (ArrayList<Info> piece : puzzle) {
			for (int j = 0; j < blank.size(); j++) {
				if (filled[j] == 1) { // 이미 채워진 공간이면 넘어가기
					continue;
				}
				ArrayList<Info> space = blank.get(j);

				if (space.size() == piece.size()) { // 해당 공간과 퍼즐 조각의 크기가 같으면
					if (check(space, piece)) { // 해당 공간에 퍼즐 조각이 들어가는지 확인
						answer += piece.size();
						filled[j] = 1;
						break;
					}
				}
			}
		}

		return answer;
	}

	static class Info implements Comparable<Info> {
		int x;
		int y;

		public Info(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Info o) {
			if (this.x == o.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
	}
}
