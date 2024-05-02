import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(int left, int right) {
        int answer = 0;

        for (int i = left; i <= right; i++) {
            List<Integer> divisor = new ArrayList<>();
            divisor = getDivisor(i, divisor);
            if (divisor.size() % 2 == 0) {
                answer += i;
            }
            if (divisor.size() % 2 == 1) {
                answer -= i;
            }
            System.out.println(divisor);
        }
        return answer;
    }

    public static List<Integer> getDivisor(int number, List<Integer> divisor) {
        for (int i = 1; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                divisor.add(i);
                divisor.add(number / i);
            }
        }

        return divisor.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}