// https://www.acmicpc.net/problem/11004
// 시간 복잡도: O(N log N) -> merge sort 직접 구현하기

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i <N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        pro(A);

        System.out.println(A[K - 1]);
    }

    static void pro(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;

        mergeSort(arr, left, mid); // log n 계속 쪼갬
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right); // 마지막에 병합
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int L = left; // 왼쪽 배열 포인터
        int R = mid + 1; // 오른쪽 배열 포인터
        int idx = 0;

        // mid 기준 왼쪽, 오른쪽 배열 모두 값이 존재하면
        while (L <= mid && R <= right) {
            if (arr[L] <= arr[R]) {
                tmp[idx] = arr[L];
                idx++;
                L++;
            } else {
                tmp[idx] = arr[R];
                idx++;
                R++;
            }
        }

        // 왼쪽 배열만 존재하면
        while (L <= mid) {
            tmp[idx] = arr[L];
            idx++;
            L++;
        }

        // 오른쪽 배열만 존재하면
        while (R <= right) {
            tmp[idx] = arr[R];
            idx++;
            R++;
        }

        for (int i = 0; i < tmp.length; i++) {
            arr[left+i] = tmp[i];
        }
    }
}
