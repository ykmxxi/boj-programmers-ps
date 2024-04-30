/*
    
    # 추억 점수
    - 사진별로 추억 점수를 매길려고 한다
    - 사진속에 나오는 인물의 그리움 점수를 모두 합산한 값이 해당 사진의 추억 점수
    - name: 그리워 하는 사람의 이름을 담은 문자열 배열
    - yearning: 각 사람별 그리움 점수를 담은 정수 배열
        - name과 yearing의 길이는 같음
    - photo: 각 사진에 찍힌 인물의 이름을 담은 2차원 문자열 배열
*/

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        for (int i = 0; i < photo.length; i++) {
            int score = 0;
            
            for (String person : photo[i]) {
                for (int j = 0; j < name.length; j++) {
                    if (person.equals(name[j])) {
                        score += yearning[j];
                    }
                }
            }
            
            answer[i] = score;
        }
        
        return answer;
    }
}