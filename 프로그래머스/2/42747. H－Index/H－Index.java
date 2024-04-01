/*
    - 어떤 과학자가 발표한 논문 n편 중
        - h번 이상 인용된 논문이 h편 이상
        - 나머지 논문이 h번 이하 인용되었다면 h의 최대값이
    - 이 과학자의 H-Index

    - 어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열이 매개변수
    - 이 과학자의 H-Index를 return
*/

import java.util.*;

class Solution {

    public static boolean check(int mid, int[] arr) {
        int val = arr[mid];

        return arr.length - mid >= val; 
    }

    public int solution(int[] citations) {
        int answer = 0;

        // 정렬 오름차순
        Arrays.sort(citations);

        for (int i = 0; i < citations.length; i++) {
            int cnt = citations.length - i;

            if (citations[i] >= cnt) {
                answer = cnt;
                break;
            }
        }


        return answer;
    }
}