/*
    - 일렬 나열 n개의 풍선, 다음 과정을 반복하며 풍선들을 단 1개만 남을 때까지 계속 터트림
        1. 임의의 인접한 두 풍선을 고른뒤, 두 풍선 중 하나를 터트림
        2. 터진 풍선으로 인해 풍선들 사이에 빈 공간이 생기면 빈 공간이 없도록 중앙으로 밀착
    - 인접한 두 풍선 중 번호가 더 작은 풍선을 터트리는 행위는 최대 1번만 가능
    - 경우에 따라 마지막까지 남기는 것이 불가능한 풍선이 존재
*/

class Solution {
    
    public int solution(int[] a) {
        if (a.length == 1) { // 풍선이 1개만 존재하면 1
            return 1;
        }
        
        int answer = 2; // 처음과 마지막 풍선은 무조건 마지막까지 남을 수 있음
        if (a.length == 2) { // 풍선이 2개만 존재하면 2
            return answer;
        }
        
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        
        int leftMin = a[0];
        int rightMin = a[a.length - 1];
        
        for (int i = 1; i < a.length - 1; i++) { // 기준이 idx일 때 왼쪽 구간의 최소값 구하기
            if (leftMin > a[i]) {
                leftMin = a[i];
            }
            left[i] = leftMin;
        }
        
        for (int i = a.length - 1; i > 0; i--) { // 기준이 idx일 때 오른쪽 구간의 최소값 구하기
            if (rightMin > a[i]) {
                rightMin = a[i];
            }
            right[i] = rightMin;
        }
        
        for (int i = 1; i < a.length - 1; i++) { // 처음과 마지막 제외
            if (left[i] < a[i] && right[i] < a[i]) { // 양 구간의 최소값이 모두 기준값보다 작으면 최후까지 남을 수 없음(적어도 2번 이상 작은걸 터트려야함)
                continue;
            }
            answer++;
        }
        
        return answer;
    }
    
}
