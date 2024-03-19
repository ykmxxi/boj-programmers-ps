/*
    - 단품으로만 제공하던 메뉴를 조합해 코스로 재구성
    - 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 구성
    - 코스요리 메뉴는 최소 2가지 이상의 단품 메뉴로 구성, 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 메뉴 후보에 포함
    
*/

import java.util.*;

class Solution {
    
    Map<String, Integer> map;
    int[] sel;
    boolean[] visit;
    Queue<String> q = new PriorityQueue<>(); // 사전 순 정렬
    
    void rec(String order, String menu, int depth, int target) {
        if (depth == target) {
            map.put(menu, map.getOrDefault(menu, 0) + 1);
        } else {
            int start = 0;
            if (depth != 0) {
                start = sel[depth - 1] + 1;
            }
            for (int i = start; i < order.length(); i++) {
                sel[depth] = i;
                rec(order, menu + order.charAt(i), depth + 1, target);
                sel[depth] = 0;
            }
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        List<String> ans = new ArrayList<>();
        
        for (int c : course) {
            map = new HashMap<>();
            
            for (String order : orders) {
                visit = new boolean[order.length()];
                sel = new int[c + 1];
                
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                StringBuilder sb = new StringBuilder();
                for (char ch : arr) {
                    sb.append(ch);
                }
                rec(sb.toString(), "", 0, c);
            }
            
            int max = 0;
            for (String k : map.keySet()) {
                if (map.get(k) >= 2) {
                    max = Math.max(max, map.get(k));
                }
            }
            
            for (String k : map.keySet()) {
                if (max == map.get(k)) {
                    ans.add(k);
                }
            }
        }
        
        Collections.sort(ans);
        
        return ans.stream().toArray(String[]::new);
    }
}