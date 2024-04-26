/*
    알아볼 수 없는 번호를 0으로 표기
    (44, 1, 0, 0, 31, 25) -> 2개의 번호를 알아볼 수 없음

    알아볼 수 없는 번호 + 맞춘 개수 -> 최고 순위
    맞춘 개수 -> 최저 순위
*/

class Solution {

	static int[] getHigherAndLower(int[] lottos, int[] win_nums) {
		int cnt = 0;
		int zeroCnt = 0;
		int higher = 0;
		int lower = 0;

		for (int lotto : lottos) {
			if (lotto == 0) {
				zeroCnt++;
				continue;
			}

			for (int winNum : win_nums) {
				if (lotto == winNum) {
					cnt++;
				}
			}
		}

		higher = 7 - cnt - zeroCnt;
		lower = 7 - cnt;

		if (lower == 7) {
			lower = 6;
		}

		if (higher == 7) {
			higher = 6;
		}

		return new int[] {higher, lower};
	}

	public int[] solution(int[] lottos, int[] win_nums) {
		int[] answer;

		answer = getHigherAndLower(lottos, win_nums);

		return answer;
	}
}
