/*
    https://school.programmers.co.kr/learn/courses/30/lessons/118666
    
    - 성격 유형 검사는 4개 지표로 성격 유형을 구분
        1번: 라이언형(R), 튜브형(T)
        2번: 콘형(C), 프로도형(F)
        3번: 제이지형(J), 무지형(M)
        4번: 어피치형(A), 네오형(N)
    - 총 16가지 유형이 나올 수 있음 
    - 총 n개의 질문이 있고, 각 질문에는 7개의 선택지가 있음
        - 매우 비동의(N형 3점), 비동의(N형 2점), 약간 비동의(N형 1점)
        - 모르겠음(어떤 유형도 점수를 얻지 않음)
        - 약간 동의(A형 1점), 동의(A형 2점), 매우 동의(A형 3점)
    - 위 예제처럼 점수를 얻는 유형이 정해져있지 않고, 질문에 따라 동의 비동의에 따라 점수를 얻는 유형이 달라질 수 있음
        - survey[0] = "RT" 이면 -> R은 질문지의 비동의,  T는 질문지의 동의
    - 같은 점수를 얻으면 사전 순으로 빠른 성격 유형을 선택
*/

class Solution {
    private static final int[] scores = new int[8];
    private static final char[] types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
    
    static int getPoint(int choice) {
        if (choice == 1 || choice == 7) {
            return 3;
        } else if (choice == 2 || choice == 6) {
            return 2;
        } else {
            return 1;
        }
    }
    
    public String solution(String[] survey, int[] choices) {
        for (int i = 0; i < survey.length; i++) {
            String s = survey[i];
            int c = choices[i];
            
            if (c == 4) {
                continue;
            }
            
            if (s.equals("RT")) {
                if (c > 4) {
                    scores[1] += getPoint(c);
                } else {
                    scores[0] += getPoint(c);
                }
            } else if (s.equals("TR")) {
                if (c > 4) {
                    scores[0] += getPoint(c);
                } else {
                    scores[1] += getPoint(c);
                }
            } else if (s.equals("CF")) {
                if (c > 4) {
                    scores[3] += getPoint(c);
                } else {
                    scores[2] += getPoint(c);
                }
            } else if (s.equals("FC")) {
                if (c > 4) {
                    scores[2] += getPoint(c);
                } else {
                    scores[3] += getPoint(c);
                }
            } else if (s.equals("JM")) {
                if (c > 4) {
                    scores[5] += getPoint(c);
                } else {
                    scores[4] += getPoint(c);
                }
            } else if (s.equals("MJ")) {
                if (c > 4) {
                    scores[4] += getPoint(c);
                } else {
                    scores[5] += getPoint(c);
                }
            } else if (s.equals("AN")) {
                if (c > 4) {
                    scores[7] += getPoint(c);
                } else {
                    scores[6] += getPoint(c);
                }
            } else {
                if (c > 4) {
                    scores[6] += getPoint(c);
                } else {
                    scores[7] += getPoint(c);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scores.length; i += 2) {
            if (scores[i] > scores[i + 1]) {
                sb.append(types[i]);
            } else if (scores[i] < scores[i + 1]) {
                sb.append(types[i + 1]);
            } else { // 같은 경우
                int a = types[i] - 'A';
                int b = types[i + 1] - 'A';
                
                if (a < b) {
                    sb.append(types[i]);
                } else {
                    sb.append(types[i + 1]);
                }
            }
        }
        
        return sb.toString();
    }
}