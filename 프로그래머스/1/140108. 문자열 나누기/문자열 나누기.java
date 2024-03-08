/*
    - 먼저 첫 글자를 읽고 x에 저장
    - 이 문자열을 왼쪽에서 오른쪽으로 읽어가면서 x와 x가 아닌 다른 글자들이 나온 횟수를 센다 (처음 x도 센다)
    - 처음으로 두 횟수가 같아지는 순간 멈추고 지금까지 읽은 문자열을 분리
    - 나머지 분자열을 빼고 남은 부분에 대해서 이 과정을 반복
    - 두 횟수가 다른 상태에서 더 읽을 문자열이 없으면 나머지를 분리하고 종료
    - 분해한 문자열의 개수를 return
*/

class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] arr = s.toCharArray();
        boolean[] visit = new boolean[s.length()];
        
        for (int i = 0; i < arr.length; i++) {
            if (visit[i]) {
                continue;
            }
            
            char x = arr[i];
            visit[i] = true;
            int last = 0;
            int xCnt = 1;
            int dCnt = 0;
            
            if (i == arr.length - 1) {
                answer++;
            }
            
            for (int j = i + 1; j < arr.length; j++) {
                visit[j] = true;
                if (x == arr[j]) {
                    xCnt++;
                } else {
                    dCnt++;
                }
                
                if (j == arr.length - 1 && xCnt != dCnt) {
                    System.out.println(arr[j]);
                    answer++;
                }
                
                if (xCnt == dCnt) {
                    answer++;
                    last = j;
                    System.out.println(arr[j]);
                    break;
                }
            }
            
            i = last;
        }
        
        return answer;
    }
}