/*
    - numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열에 오름차순으로 담아 return
    - 만들 수 있는 최대 수: 200
*/

import java.util.ArrayList;

class Solution {
	static int[] selected, visit, used;
	static ArrayList<Integer> list;

	static void rec_func(int k, int[] arr) {
		if (k == 2) {
			int num = arr[selected[0]] + arr[selected[1]];
			if (visit[num] == 0) {
				list.add(num);
				visit[num] = 1;
			}
		} else {
			for (int i = 0; i < arr.length; i++) {
                if (used[i] == 1) {
                    continue;
                }
				selected[k] = i;
                used[i] = 1;
                
				rec_func(k + 1, arr);
                
				selected[k] = 0;
                used[i] = 0;
			}
		}
	}

	public int[] solution(int[] numbers) {
		// numbers.length개 중 중복 없이 2개를 뽑기 (조합)
		selected = new int[3];
		visit = new int[201];
        used = new int[numbers.length];
		list = new ArrayList<>();

		rec_func(0, numbers);

		return list.stream()
			.mapToInt(Integer::intValue)
			.sorted()
			.toArray();
	}

}
