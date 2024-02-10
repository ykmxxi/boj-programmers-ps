/*
    https://school.programmers.co.kr/learn/courses/30/lessons/42748
    
    배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하는 문제
*/

import java.util.*;

class Solution {
    
    public static int getK(int[] command, int[] array) {
        int i = command[0];
        int j = command[1];
        int k = command[2];
        ArrayList<Integer> tmp = new ArrayList<>();
        
        for (int idx = i - 1; idx < j; idx++) {
            tmp.add(array[idx]);
        }
        
        Collections.sort(tmp);
        return tmp.get(k - 1);
    }
    
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length]; // 명령의 개수 = 정답 배열의 크기
        
        for (int i = 0; i < commands.length; i++) {
            answer[i] = getK(commands[i], array);
        }
        
        return answer;
    }
}