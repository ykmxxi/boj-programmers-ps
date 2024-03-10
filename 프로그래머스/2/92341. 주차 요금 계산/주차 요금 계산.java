/*
    - 요금표와 차량의 입차, 출차 기록이 주어졌을 때 차량별 주차 요금 계산
    - 요금표: 기본 시간(분), 기본 요금, 단위 시간, 단위 요금  
*/

import java.util.*;

class Solution {
    
    public int[] solution(int[] fees, String[] records) {
        List<Integer> ans = new ArrayList<>();
        
        Map<String, Info> map = new HashMap<>();
        
        for (String r : records) {
            String[] tokens = r.split(" ");
            String[] t = tokens[0].split(":");
            
            int time = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
            
            if (map.get(tokens[1]) == null) {
                map.put(tokens[1], new Info());
            }
            map.get(tokens[1]).add(tokens[2], time);
        }
        
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        
        for (String k : keys) {
            Info info = map.get(k);
            int totalT = 0;
            int sum = 0;
            
            if (info.in.size() != info.out.size()) {
                info.add("OUT", 23 * 60 + 59);
            }
            
            for (int i = 0; i < info.in.size(); i++) {
                totalT += (info.out.get(i) - info.in.get(i));
            }
            
            if (totalT <= fees[0]) {
                sum += fees[1];
            } else {
                sum += fees[1] + Math.ceil((double)(totalT - fees[0]) / fees[2]) * fees[3];
            }
            
            ans.add(sum);
        }
        
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
    
    static class Info {
        List<Integer> in;
        List<Integer> out;
        
        Info () {
            this.in = new ArrayList<>();
            this.out = new ArrayList<>();
        }
        
        void add(String str, int time) {
            if ("IN".equals(str)) {
                in.add(time);
            } else {
                out.add(time);
            }
        }
    }
}