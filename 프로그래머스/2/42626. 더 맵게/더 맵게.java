import java.util.PriorityQueue;

class Solution {
	public int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int num : scoville) {
			pq.add(num);
		}

		while (true) {
			if (pq.size() == 1 && pq.peek() < K) {
				answer = -1;
				break;
			}
			if (pq.peek() >= K) { // 스코빌 지수가 가장 낮은 음식이 K 이상이면 stop
				break;
			}

			int sum = pq.poll() + pq.poll() * 2;
			pq.add(sum);
			answer++;
		}

		return answer;
	}
}