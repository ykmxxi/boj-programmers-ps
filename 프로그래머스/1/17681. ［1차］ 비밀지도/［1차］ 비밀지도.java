class Solution {
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String[] binaryArr1 = getBinary(arr1, n);
        String[] binaryArr2 = getBinary(arr2, n);

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (binaryArr1[i].charAt(j) == '0' && binaryArr2[i].charAt(j) == '0') {
                    sb.append(" ");
                } else {
                    sb.append("#");
                }
            }
            answer[i] = sb.toString();
        }
        return answer;
    }

    public static String[] getBinary(int[] arr, int n) {
        String[] binaryArr = new String[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (Integer.toBinaryString(arr[i]).length() != n) {
                StringBuilder result = new StringBuilder(Integer.toBinaryString(arr[i]));
                for (int j = 0; j < n - Integer.toBinaryString(arr[i]).length(); j++) {
                    result.insert(0, "0");
                }
                binaryArr[i] = result.toString();
            } else {
                binaryArr[i] = Integer.toBinaryString(arr[i]);
            }
        }
        return binaryArr;
    }
}