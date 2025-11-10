import java.util.*;

class Solution {
    static int vh;
    static int ph;
    static int osh;
    static int oeh;
    
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String[] tokens = video_len.split(":");
        vh = Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]);
        tokens = pos.split(":");
        ph = Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]);
        tokens = op_start.split(":");
        osh = Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]);
        tokens = op_end.split(":");
        oeh = Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]);
        
        
        return pro(commands);
    }
    
    String pro(String[] cms) {
        // 10초 전 이동: prev, (10초 미만인 경우 처음으로) -> 0분 0초
        // 1-초 후 이동: next, (10초 미만인 경우 마지막 위치)
        int cur = ph;
        for (int i = 0; i < cms.length; i++) {
            if (cur >= osh && cur <= oeh) {
                cur = oeh;
            }
            if (cms[i].equals("next")) {
                if (cur + 10 > vh) {
                    cur = vh;
                } else {
                    cur += 10;
                }
            } else if (cms[i].equals("prev")) {
                if (cur - 10 < 0) {
                    cur = 0;
                } else {
                    cur -= 10;
                }
            }
            if (cur >= osh && cur <= oeh) {
                cur = oeh;
            }
        }
        int minute = cur / 60;
        int seconds = cur % 60;
        StringBuilder sb = new StringBuilder();
        if (minute < 10) {
            sb.append("0");
        }
        sb.append(minute).append(":");
        if (seconds < 10) {
            sb.append("0");
        }
        sb.append(seconds);
        return sb.toString();
    }
}