/*
    https://school.programmers.co.kr/learn/courses/30/lessons/87390
    
    - n: 행과 열의 크기
    - left:
    - right:
    
    다음 과정을 거쳐서 1차원 배열을 만든다
    1. n행 n열 크기의 비어있는 배열 생성
    2. i = 1, 2, 3, ..., n에 대해 다음 과정 반복
        - 1행 1열부터 i행 i열까지의 영역 내의 모든 빈 칸을 숫자 i로 채운다
    3. 1행, 2행, ..., n행을 잘라내어 모두 이어붙은 새로운 1차원 배열을 만든다
    4. 새로운 1차원 배열을 arr 이라 할 때, arr[left], arr[left + 1], ... , arr[right]만 남기고 나머지는 지운다
    
*/
import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int len = (int)(right - left + 1);
        int[] answer = new int[len];
        
        // 2, 3, 4 과정
        // 12345 22345 33345 44445 55555
        // 01234 56789
        // 1행 2행 3행 4행 5행
        
        for (int i = 0; i < answer.length; i++) {
            int row = (int)(left / n + 1);
            int col = (int)(left % n + 1);
            
            if (row >= col) {
                answer[i] = row;
            } else {
                answer[i] = col;
            }
            left++;
        }
        
        return answer;
    }
}