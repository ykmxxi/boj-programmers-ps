import java.util.*;

class Solution {

    static boolean check(String str) {
        return str.equals("zero") || str.equals("one") || str.equals("two") || str.equals("three")
           || str.equals("four") || str.equals("five") || str.equals("six") || str.equals("seven")
           || str.equals("eight") || str.equals("nine");
    }

    static char change(String str)  {
        if (str.equals("zero")) {
            return '0';
        } else if (str.equals("one")) {
            return '1';
        } else if (str.equals("two")) {
            return '2';
        } else if (str.equals("three")) {
            return '3';
        } else if (str.equals("four")) {
            return '4';
        } else if (str.equals("five")) {
            return '5';
        } else if (str.equals("six")) {
            return '6';
        } else if (str.equals("seven")) {
            return '7';
        } else if (str.equals("eight")) {
            return '8';
        } else {
            return '9';
        }
    }

    public int solution(String s) {
        int answer = 0;

        StringBuilder sb = new StringBuilder();
        int[] visit = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            StringBuilder temp = new StringBuilder();

            if (visit[i] == 1) {
                continue;
            }

            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                visit[i] = 1;
                sb.append(s.charAt(i));
            } else {
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                        break;
                    }
                    if (check(temp.toString())) {
                        break;
                    }
                    temp.append(s.charAt(j));
                    visit[j] = 1;
                }
                sb.append(change(temp.toString()));
            }
        }
        answer = Integer.parseInt(sb.toString());
        return answer;
    }
}