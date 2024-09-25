/*
    - 31일: 1월, 3월, 5월, 7월, 8월, 10월, 12월
    - 30일: 4월, 6월, 9월, 11월
    - 29일: 2월
*/
class Solution {

    public String solution(int a, int b) {
        String[] week = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        String[][] calendar = new String[13][32];
        
        int cur = 5; // 1월 1일 기준 금요일
        for (int i = 1; i <= 12; i++) {
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                for (int j = 1; j <= 31; j++) {
                    calendar[i][j] = week[cur % 7];
                    cur++;
                }
            } else if (i == 2) {
                for (int j = 1; j <= 29; j++) {
                    calendar[i][j] = week[cur % 7];
                    cur++;
                }
            } else {
                for (int j = 1; j <= 30; j++) {
                    calendar[i][j] = week[cur % 7];
                    cur++;
                }
            }
        }
        return calendar[a][b];
    }

}
