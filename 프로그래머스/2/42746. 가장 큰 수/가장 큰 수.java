/*
    0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 찾기
    numbers의 길이는 1 이상 100,000 이하
    numbers의 원소는 0 이상 1,000 이하
*/

import java.util.*;

class Solution {
    
    public String solution(int[] numbers) {
        // 정렬 조건:
        // 길이가 같을 때: 각 자리 숫자를 비교하며 먼저 큰 수가 등장한 수를 먼저 배치 (9 > 10)
        // 길이가 다를 때: 각 자리 숫자를 비교하며 먼저 큰 수가 등장한 수를 먼저 배치하고, 짧은 쪽의 마지막 숫자보다 뒤에 더 큰게 나온다면 앞으로
        // s1: 3, s2: 30 -> 330(s1 + s2) > 303(s2 + s1)
        // s1: 3, s2: 34 -> 334(s1 + s2) < 343(s2 + s1)
        
        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.concat(s1).compareTo(s1.concat(s2));
            }
        });
        
        if (arr[0].equals("0")) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str);
        }
        return sb.toString();
    }
}