/*  
    - 게임 화면은 1 x 1 크기의 칸들로 이루어진 N x N 크기의 정사각 격자
        - 왼쪽: 크레인, 오른쪽: 바구니
        - 2차원 격자 -> 그래프 문제?
    - 모든 인형은 1 x 1 크기의 격자 한 칸을 차지, 가장 아래 칸부터 쌓여 있다
    - 크레인은 좌우로 움직여서(열 이동) 제일 위에 있는 인형을 집어 올릴 수 있음(해당 열에서 가장 작은 값을 가진 행)
    - 같은 모양의 인형 두 개가 바구니에 연속해서 쌓이면 두 인형은 터뜨려지면서 바구니에서 사라짐
        - Stack
    - 크레인은 인형을 무조건 집을 수 있음, 인형이 없는 곳에서 작동시키면 아무런 일도 일어나지 않음
    - 바구니는 모든 인형이 들어갈 수 있을 만큼 충분히 큼
    
    로직
    1. moves를 순회하며 크레인을 이동시킨다.
    2. 해당 열의 제일 위 인형을 조회해서 꺼낸다. 꺼내면 방문 처리
    3. 바구니(스택)에 담는다.
    4. last 값과 같으면 바구니에서 없애고 정답 += 2
    
*/

import java.util.*;

class Solution {
    static Stack<Integer> bucket;
    static int[][] visit; // 꺼낸 칸은 방문 처리
    
    static int play(int[][] board, int col) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col - 1] != 0 && visit[i][col - 1] == 0) {
                visit[i][col - 1] = 1; // 방문 처리
                return board[i][col - 1];
            }
        }
        
        return 0;
    }
    
    static int check(int cur) {
        if (bucket.isEmpty()) { // 현재 바구니가 비어져 있으면
            bucket.push(cur); // 인형을 추가하고 종료
            return 0;
        }
        
        int last = bucket.peek();
        
        if (last == cur) { // 같으면
            bucket.pop(); // 바구니에서 없애고 
            return 2; // 2를 돌려준다.
        }
        
        bucket.push(cur); // 같지 않으면 현재 뽑은 인형을 바구니에 추가하고
        return 0; // 0을 돌려준다.
        
    }
    
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int doll = 0;
        
        bucket = new Stack<>();
        visit = new int[board.length][board[0].length];
        
        for (int move : moves) {
            // 2. 인형 꺼내기
            doll = play(board, move);
            
            
            // 3 & 4. 꺼낸 인형이 있으면 확인하고 담거나, 없애기
            if (doll != 0) {
                answer += check(doll);
            }
            
        }
        
        return answer;
    }
}