import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> totalCnt = new HashMap<>();
        Map<String, List<Info>> infos = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            totalCnt.put(genres[i], totalCnt.getOrDefault(genres[i], 0) + plays[i]);
            infos.put(genres[i], new ArrayList<>());
        }
        
        for (int i = 0; i < genres.length; i++) {
            infos.get(genres[i]).add(new Info(plays[i], i));
        }
        
        List<String> keys = new ArrayList<>(totalCnt.keySet());
        keys.sort((o1, o2) -> totalCnt.get(o2) - totalCnt.get(o1));
        
        for (String key : keys) {
            List<Info> cnts = infos.get(key);
            Collections.sort(cnts);
            
            if (cnts.size() > 1) {
                ans.add(cnts.get(0).num);
                ans.add(cnts.get(1).num);
            } else {
                ans.add(cnts.get(0).num);
            }
            
        }
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
    
    static class Info implements Comparable<Info> {
        int plays;
        int num;
        
        Info(int plays, int num) {
            this.plays = plays;
            this.num = num;
        }
        
        @Override
        public int compareTo(Info o) {
            return o.plays - this.plays;
        }
    }
    
}