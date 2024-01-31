/*
    https://school.programmers.co.kr/learn/courses/30/lessons/43165
    
    # 타겟 넘버
    - n개의 음이 아닌 정수들이 존재
    - 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 한다
        - [1, 1, 1, 1, 1]로 숫자 3을 만들기
        - -1+1+1+1+1 = 3
        - +1-1+1+1+1 = 3
        - +1+1-1+1+1 = 3
        - +1+1+1-1+1 = 3
        - +1+1+1+1-1 = 3
        - 총 5가지 방법 존재
    - 사용할 수 있는 숫자가 담긴 배열 numbers, 목표 숫자 target이 있을 때 더하고 빼서 target을 만드는 방법의 수
    
    ## 제한 사항
    - 주어지는 숫자의 개수는 2개 이상 20개 이하
    - 각 숫자는 1 이상 50 이하인 자연수
    - 타겟 넘버는 1 이상 1000 이하
*/

class Solution {
    static int answer, target;
    static int[] numbers;
    
    
    public void dfs(int depth, int curNum) {
        if (depth == numbers.length) {
            if (curNum == target) {
                answer++;
            }
            return;
        }
        
        dfs(depth + 1, curNum + numbers[depth]);
        dfs(depth + 1, curNum - numbers[depth]);
        
    }
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        this.numbers = numbers;
        this.target = target;
        
        dfs(0, 0);
        
        return answer;
    }
}