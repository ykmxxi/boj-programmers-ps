import java.io.*;
import java.util.*;

// 시간 복잡도: O(N log M) (M = A의 최댓값)
// 공간 복잡도: O(N + M)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] A;
    
    // 세그먼트 트리 (값의 범위를 인덱스로 사용)
    // dp[val] = 'val'을 마지막으로 하는 최대 합
    static long[] tree; 
    static int maxVal = 0; // A에 등장하는 최댓값

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            maxVal = Math.max(maxVal, A[i]); // 세그먼트 트리 크기 결정을 위해 최댓값 찾기
        }
    }

    /**
     * 세그먼트 트리 업데이트 (targetIdx에 value를 갱신)
     * @param node 현재 노드 인덱스 (1부터 시작)
     * @param start 현재 노드가 커버하는 범위 시작
     * @param end 현재 노드가 커버하는 범위 끝
     * @param targetIdx 갱신할 값의 인덱스 (A[i]의 값)
     * @param value 갱신할 값 (새로운 최대 합)
     */
    static void update(int node, int start, int end, int targetIdx, long value) {
        // 범위를 벗어난 경우
        if (targetIdx < start || targetIdx > end) {
            return;
        }

        // 리프 노드 (값을 찾음)
        if (start == end) {
            // [중요] 기존 값보다 현재 계산된 합이 더 클 때만 갱신
            // (이 문제에서는 덮어써도 되지만, max(tree[node], value)가 더 범용적)
            tree[node] = value; 
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, targetIdx, value); // 왼쪽 자식
        update(node * 2 + 1, mid + 1, end, targetIdx, value); // 오른쪽 자식
        
        // 자식 노드의 값 중 최댓값을 부모 노드에 저장
        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }

    /**
     * 세그먼트 트리 쿼리 (queryLeft ~ queryRight 범위의 최댓값 탐색)
     * @param node 현재 노드 인덱스 (1부터 시작)
     * @param start 현재 노드가 커버하는 범위 시작
     * @param end 현재 노드가 커버하는 범위 끝
     * @param queryLeft 쿼리 범위 시작
     * @param queryRight 쿼리 범위 끝
     * @return 쿼리 범위 내의 최댓값
     */
    static long query(int node, int start, int end, int queryLeft, int queryRight) {
        // 1. 쿼리 범위가 노드 범위를 완전히 벗어난 경우
        if (queryRight < start || queryLeft > end) {
            return 0; // 합계에 영향을 주지 않는 값 (최대 합이므로 0)
        }
        
        // 2. 노드 범위가 쿼리 범위에 완전히 포함되는 경우
        if (queryLeft <= start && end <= queryRight) {
            return tree[node];
        }

        // 3. 쿼리 범위와 노드 범위가 겹치는 경우
        int mid = (start + end) / 2;
        long leftMax = query(node * 2, start, mid, queryLeft, queryRight);
        long rightMax = query(node * 2 + 1, mid + 1, end, queryLeft, queryRight);
        
        return Math.max(leftMax, rightMax);
    }


    static void pro() {
        // 세그먼트 트리 초기화. 크기는 A의 최댓값(maxVal)에 따라 결정
        // 트리의 높이 H = ceil(log2(maxVal)), 배열 크기 = 2^(H+1)
        // 편의상 4 * maxVal로 잡음
        tree = new long[4 * maxVal];
        
        long maxTotalSum = 0; // 최종 정답 (전체 최대 합)

        for (int i = 0; i < N; i++) {
            int currentValue = A[i];
            
            // 1. 쿼리: 1 ~ (currentValue - 1) 범위의 최대 합을 찾는다.
            // (A[i]보다 작은 값으로 끝나는 최대 합)
            long maxPrevSum = 0;
            if (currentValue > 1) { // currentValue가 1이면 쿼리 범위가 없음
                 maxPrevSum = query(1, 1, maxVal, 1, currentValue - 1);
            }

            // 2. 현재 합 계산: (이전 최대 합 + 현재 값)
            long currentSum = maxPrevSum + currentValue;
            
            // 3. 업데이트: 트리의 currentValue 위치에 currentSum을 갱신
            // (currentValue로 끝나는 최대 합은 currentSum이다)
            update(1, 1, maxVal, currentValue, currentSum);
            
            // 4. 최종 정답 갱신
            maxTotalSum = Math.max(maxTotalSum, currentSum);
        }

        System.out.println(maxTotalSum);
    }
}
