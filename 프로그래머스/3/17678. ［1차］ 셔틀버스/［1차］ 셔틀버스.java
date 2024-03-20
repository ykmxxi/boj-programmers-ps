/*
    - 셔틀은 09:00 부터 총 n회 t분 간격으로 역에 도착하며 최대 m명이 탑승 가능
    - 대기 순서대로 태우고 출발
    - 셔틀을 타고 사무실로 갈 수 있는 도착 시간 중 제일 늦은 시각 구하기
    - 콘은 게을러 같은 시각에 도착한 크루 중 대기열 제일 뒤에 선다, 모든 크루는 23:59에 집에 돌아간다
*/

import java.util.*;

class Solution {
    
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        Deque<Integer> q = new ArrayDeque<>();
        for (String time : timetable) {
            String[] token = time.split(":");
            q.offer(Integer.parseInt(token[0]) * 60 + Integer.parseInt(token[1]));
        }
        
        int last = 540 + (n - 1) * t; // 그 날의 마지막 셔틀 도착 시간
        if (q.peek() > last) { // 제일 먼저 서 있는 사람이 마지막 도착 후에 서있으면 모두 집으로
            String hour = String.format("%02d", last / 60);
            String minute = String.format("%02d", last % 60);
            return hour + ":" + minute;
        }
        
        int start = 540;
        int ans = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt = 0;
            
            while (!q.isEmpty()) {
                int cur = q.peek();
                if (cur <= start && cnt < m) {
                    q.poll();
                    cnt++;
                } else {
                    break;
                }
                ans = cur - 1; // 각 셔틀에 탄 마지막 사람의 시간 저장
            }
            start += t;
        }
        
        if (cnt < m) { // 마지막으로 운행된 셔틀에 탑승한 인원이 m명 미만이면 마지막 셔틀 탑승 가능
            ans = start - t;
        }
        
        String hour = String.format("%02d", ans / 60);
        String minute = String.format("%02d", ans % 60);
        return hour + ":" + minute;
    }
    
}
