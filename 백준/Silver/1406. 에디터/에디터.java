import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int M = Integer.parseInt(br.readLine());

        // 1. LinkedList 준비 (Doubly Linked List 기반)
        LinkedList<Character> list = new LinkedList<>();
        for (char ch : str.toCharArray()) {
            list.add(ch);
        }

        // 2. ListIterator 생성 (커서를 리스트의 맨 끝으로 설정)
        // list.size()를 인자로 주면 초기 커서가 마지막 요소 다음에 위치함
        ListIterator<Character> iter = list.listIterator(list.size());

        for (int i = 0; i < M; i++) {
            String command = br.readLine();
            char op = command.charAt(0);

            switch (op) {
                case 'L':
                    if (iter.hasPrevious()) {
                        iter.previous(); // 커서를 왼쪽으로 이동
                    }
                    break;
                case 'D':
                    if (iter.hasNext()) {
                        iter.next(); // 커서를 오른쪽으로 이동
                    }
                    break;
                case 'B':
                    if (iter.hasPrevious()) {
                        iter.previous(); // 삭제 대상을 가리키기 위해 왼쪽으로 이동 후
                        iter.remove();   // 해당 요소 삭제
                    }
                    break;
                case 'P':
                    iter.add(command.charAt(2)); // 현재 커서 위치에 추가 (커서는 추가된 문자 뒤로 자동 이동)
                    break;
            }
        }

        // 3. 결과 출력 (StringBuilder 활용)
        StringBuilder sb = new StringBuilder();
        for (char ch : list) {
            sb.append(ch);
        }
        System.out.print(sb);
    }
}