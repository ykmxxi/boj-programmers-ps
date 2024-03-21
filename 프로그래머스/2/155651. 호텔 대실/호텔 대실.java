/*
    - 최소한의 객실(최소값)로 예약 손님을 받음
    - 사용한 객실은 퇴실 시간 기준 10분간 청소하고 다음 손님이 이용 가능
*/

import java.util.*;

class Solution {
    
    public int solution(String[][] book_time) {
        int answer = 0;
        
        Arrays.sort(book_time, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0].compareTo(o2[0]); // 시작 시간이 빠른 순서대로
            }
            return o1[1].compareTo(o2[1]); // 시작 시간이 같으면 끝나는 시간이 빠른대로
        });
        
        Map<Integer, Integer> map = new HashMap<>(); // key:방, value:현재 손님의 끝나는 시간
        int num = 1;
        for (String[] book : book_time) {
            String[] s = book[0].split(":");
            String[] e = book[1].split(":");

            int start = toTime(s);
            int end = toTime(e);
            
            if (map.isEmpty()) { // 방이 아예 없으면 첫 손님 방을 추가
                map.put(num, end);
            } else {
                boolean add = true;
                for (int key : map.keySet()) { // 기존의 방에 손님이 입실 가능하면
                    if (map.get(key) + 10 <= start) {
                        map.put(key, end);
                        add = false;
                        break;
                    }
                }
                if (add) { // 만약 새로운 방이 필요하면
                    map.put(num + 1, end);
                    num++;
                }
            }
        }
        
        return map.size();
    }
    
    int toTime(String[] token) {
        return Integer.parseInt(token[0]) * 60 + Integer.parseInt(token[1]);
    }
    
}
