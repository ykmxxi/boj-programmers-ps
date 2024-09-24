/*    
    - U: 위쪽으로 한 칸 가기
    - D: 아래쪽으로 한 칸 가기
    - R: 오른쪽으로 한 칸 가기
    - L: 왼쪽으로 한 칸 가기
    - 처음 방문한 길만 방문 길이에 포함  
*/

import java.util.*;

class Solution {

    public int solution(String dirs) {
        int answer = 0;
        
        Set<String> set = new HashSet<>();
        int[][] pos = new int[2][2]; // 현재 위치와 이전 위치 저장 
        
        for (int i = 0; i < dirs.length(); i++) {
			char direction = dirs.charAt(i);
			// 이전 위치 저장
			pos[1][0] = pos[0][0];
			pos[1][1] = pos[0][1];
			
			boolean flag = false;
			switch (direction) {
			case 'U':
				pos[0][1]++;
				if (pos[0][1] > 5) {
					flag = true;
					pos[0][1] = 5;
				}
				break;
			case 'D':
				pos[0][1]--;
				if (pos[0][1] < -5) {
					flag = true;
					pos[0][1] = -5;
				}
				break;
			case 'R':
				pos[0][0]++;
				if (pos[0][0] > 5) {
					flag = true;
					pos[0][0] = 5;
				}
				break;
			case 'L':
				pos[0][0]--;
				if (pos[0][0] < -5) {
					flag = true;
					pos[0][0] = -5;
				}
				break;
			default:
				break;
			}

			if (flag) continue;

			set.add("" + pos[0][0] + pos[0][1] + pos[1][0] + pos[1][1]);
			set.add("" + pos[1][0] + pos[1][1] + pos[0][0] + pos[0][1]);
		}
        
        answer = set.size() / 2;        
        return answer;
    }

}
