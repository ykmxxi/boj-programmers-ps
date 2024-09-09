/*
    - 한 명의 선수를 제외하고 완주, 완주하지 못한 선수의 이름을 찾기
    - participant 길이 1 ~ 100,000, completion 길이는 participant 길이 - 1
    -
*/

import java.util.*;

class Solution {

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for (String key : participant) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (String com : completion) {
            if (map.containsKey(com)) {
                int cnt = map.get(com) - 1;
                map.put(com, cnt);
            }
        }

        for (String key : map.keySet()) {
            if (map.get(key) > 0) {
                return key;
            }
        }
        return answer;
    }

}
