/*
    s의 각 위치마다 자신보다 앞에 나왔으면서, 자신과 가장 가까운 곳에 있는 글자가 어디 있는지
*/

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] used = new int[26]; // 알파벳은 총 26개
        
        for (int i = 0; i < used.length; i++) {
            used[i] = -1;
        }
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (used[ch - 'a'] == -1) { // 처음 나온 알파벳이면
                answer[i] = -1;
                used[ch - 'a'] = i;
            } else { // 처음 나온 알파벳이 아니면
                answer[i] = i - used[ch - 'a'];
                used[ch - 'a'] = i;
            }
        }
        
        return answer;
    }
}