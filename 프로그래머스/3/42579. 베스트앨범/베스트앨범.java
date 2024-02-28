/*
    - 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범 출시
    - 노래: 고유 번호로 구분
    - 수록 기준
        1. 속한 노래가 가장 <많이 재생된 장르> 수록
        2. 장르 내에서 <많이 재생된> 노래를 먼저 수록
        3. 장르 내에서 횟수가 같으면 고유 번호가 낮은 노래를 수록
*/

import java.util.*;

class Solution {
    
    static class Info implements Comparable<Info> {
        int num;
        int play;
        
        Info(int num, int play) {
            this.num = num;
            this.play = play;
        }
        
        @Override
        public int compareTo(Info o) {
            if (this.play != o.play) {
                return o.play - this.play; // 재생 횟수 내림차순
            }
            return this.num - o.num; // 고유 번호 오름차순
        }
        
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> counts = new HashMap<>();
        Map<String, List<Info>> infos = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            counts.put(genre, counts.getOrDefault(genre, 0) + plays[i]);
            infos.computeIfAbsent(genre, k -> new ArrayList<>()).add(new Info(i, plays[i]));
        }
        
        List<String> keys = new ArrayList<>(counts.keySet());
        keys.sort((o1, o2) -> counts.get(o2) - counts.get(o1)); // 장르별 총 재생횟수 내림차순 정렬
        List<Integer> ans = new ArrayList<>();
        
        for (String k : keys) {
            List<Info> l = infos.get(k);
            Collections.sort(l);
            
            ans.add(l.get(0).num);
            if (l.size() >= 2) {
                ans.add(l.get(1).num);
            }
        }
        
        return ans.stream().mapToInt(i -> i).toArray();
        
    }
}