/*
    - 데이터: 코드번호, 제조일, 최대수량, 현재수량
    - ext: 정보 기준, val_ext: 기준값, sort_by: 정렬할 기준이 되는 문자열
    - data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후(미만), sort_by를 기준으로 오름차순 정렬
*/

import java.util.*;

class Solution {
    
    boolean check(Info info, String ext, int val_ext) {
        if ("code".equals(ext)) {
            if (info.code < val_ext) {
                return true;
            }
        }
        if ("date".equals(ext)) {
            if (info.date < val_ext) {
                return true;
            }
        }
        if ("maximum".equals(ext)) {
            if (info.max < val_ext) {
                return true;
            }
        }
        if ("remain".equals(ext)) {
            if (info.remain < val_ext) {
                return true;
            }
        }
        return false;
    }
    
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<Info> list = new ArrayList<>();
        for (int[] d : data) {
            Info info = new Info(d[0], d[1], d[2], d[3]);
            if (check(info, ext, val_ext)) {
                list.add(info);
            }
        }
        
        if ("code".equals(sort_by)) {
            Collections.sort(list, (o1, o2) -> o1.code - o2.code);
        }
        if ("date".equals(sort_by)) {
            Collections.sort(list, (o1, o2) -> o1.date - o2.date);
        }
        if ("maximum".equals(sort_by)) {
            Collections.sort(list, (o1, o2) -> o1.max - o2.max);
        }
        if ("remain".equals(sort_by)) {
            Collections.sort(list, (o1, o2) -> o1.remain - o2.remain);
        }
        
        int[][] answer = new int[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            Info info = list.get(i);
            answer[i][0] = info.code;
            answer[i][1] = info.date;
            answer[i][2] = info.max;
            answer[i][3] = info.remain;
        }
        return answer;
    }
    
    static class Info {
        int code;
        int date;
        int max;
        int remain;
        
        Info(int code, int date, int max, int remain) {
            this.code = code;
            this.date = date;
            this.max = max;
            this.remain = remain;
        }
    }
}