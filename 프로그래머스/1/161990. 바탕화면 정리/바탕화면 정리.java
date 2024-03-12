/*
    - n x m 격자형 배열: 빈칸 ".", 파일 "#"
    - 최소한의 이동거리를 갖는 한 번의 드래고 모든 파일을 선택해서 한 번에 지우기
    - 한 번에 모두 삭제할 수 있는 드래그의 거리를 구하기
*/

class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        
        int sx = Integer.MAX_VALUE, sy = Integer.MAX_VALUE; 
        int ex = Integer.MIN_VALUE, ey = Integer.MIN_VALUE;
        
        for (int i = 0; i < wallpaper.length; i++) {
            String s = wallpaper[i];
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '#') {
                    sx = Math.min(sx, i);
                    sy = Math.min(sy, j);
                    ex = Math.max(ex, i + 1);
                    ey = Math.max(ey, j + 1);
                }
            }
        }
        
        answer[0] = sx;
        answer[1] = sy;
        answer[2] = ex;
        answer[3] = ey;
        return answer;
    }
}