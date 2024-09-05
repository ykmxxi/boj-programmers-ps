import java.util.*;

/*
    - 스코빌 지수 K 이상으로 만들기, 스코빌 지수가 가장 낮은 두 개의 음식을 섞음
    - 섞은 음식 = 가장 안매운 음식 + (두 번째로 안매운 음식 * 2)
    - 배열의 길이: 2 ~ 10^6, K(기준값): 0 ~ 10^9
    - 배열의 각 요소: 0 ~ 10^6
    - 최악의 경우: 모두 10^6, 10^6 길이 -> 2*10^6을 10^6번 더하기 -> long
*/

class Solution {

    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(); // 오름차순 정렬
        for (int sco : scoville) {
            pq.offer((long) sco);
        }
        
        long min = pq.peek();
        while (min < K) {
            if (pq.isEmpty() || pq.size() == 1) {
                return -1;
            }
            long nf = mix(pq.poll(), pq.poll());
            pq.offer(nf);
            min = pq.peek();
            answer++;
        }
        return answer;
    }
    
    public long mix(long a, long b) {
        return a + (2 * b);
    }

}
