import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>(); // Key: 크기, Value: 개수
        
        for (int type : tangerine) {
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        
        List<Integer> keys = new ArrayList<>(map.keySet());
        keys.sort((o1, o2) -> map.get(o2) - map.get(o1));
        
        for (int key : keys) {
            if (k <= 0) {
                break;
            }
            k -= map.get(key);
            answer++;
        }
        
        return answer;
    }
}