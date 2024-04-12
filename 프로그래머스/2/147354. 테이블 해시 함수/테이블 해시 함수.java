/*
    - 모두 정수 타입, 테이블은 2차원 행렬로 표현가능(열: 컬럼, 행: 튜플)
    - 첫 번째 컬럼(열)은 PK, 모든 튜플(행)에 대해 그 값이 중복X
    - 해시 함수
        1. 해시 함수는 col, row_begin, row_end 입력받음
        2. 테이블의 튜플은 col번째 컬럼(열)의 값을 기준으로 오름차순 정렬, 값이 동일하면 PK인 첫 번째 컬럼 기준 내림차순
        3. 정렬된 데이터에서 S_i를 i번째 행의 튜플에 대해 각 컬럼의 값을 i로 나눈 나머지들의 합으로 정의
        4. row_begin <= i <= row_end 인 모든 S_i를 누적하여 bitwisw XOR 한 값을 해시로 반환
*/

import java.util.*;

class Solution {
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] != o2[col - 1]) {
                return o1[col - 1] - o2[col - 1]; // 오름차순
            }
            return o2[0] - o1[0];
        });
        
        List<Integer> numbers = new ArrayList<>();
        for (int i = row_begin - 1; i <= row_end - 1; i++) {
            int mod = i + 1;
            int sum = 0;
            
            for (int a : data[i]) {
                sum += a % mod;
            }
            numbers.add(sum);
        }
        
        if (numbers.size() == 1) {
            return 0;
        }
        if (numbers.size() == 2) {
            return numbers.get(0) ^ numbers.get(1);
        }
        int ans = numbers.get(0) ^ numbers.get(1);
        for (int i = 2; i < numbers.size(); i++) {
            ans = ans ^ numbers.get(i);
        }
        return ans;
    }
    
}
