/*
    - 문자열에서 단어만 뒤집는다
    - 알파벳 소문자(a-z), 숫자(0-9), 공백(' '), 특수 문자(< >) 로만 이루어짐
    - 문자열의 시작과 끝은 공백이 아님
    - <와 >가 번갈아가면서 등장하고, < 이 먼저 등장
    - <> 태그는 길이가 3이상인 부분 문자열, 태그 사이에는 알파벳 소문자와 공백만 있음
    - 단어: 알파벳 소문자와 숫자로만 이루어진 부분 문자열, 연속하는 두 단어는 공백 하나로 구분, 태그는 단어가 아님

 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String S;
    static List<String> words;

    static void input() throws IOException {
        S = br.readLine();
    }

    static void pro() {
        Deque<Character> dq = new ArrayDeque<>();

        boolean tag = false;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '<') { // 태그를 만나면
                tag = true; // 표시를 해주고
                while (!dq.isEmpty()) { // 현재 스택에 쌓여있는 것이 모두 비어질 때까지
                    sb.append(dq.pop());
                }
                sb.append(S.charAt(i));
            } else if (S.charAt(i) == '>') { // 태그 종료를 만나면
                tag = false; // 표시를 해주고
                sb.append(S.charAt(i)); // 태그 >를 붙여준다
            } else if (tag) { // 현재 태그 안이면
                sb.append(S.charAt(i)); // 단어가 아니기때문에 그대로 출력
            } else { // 현재 태그 밖이면 단어이기 때문에
                if (S.charAt(i) == ' ') { // 공백을 만나면
                    while (!dq.isEmpty()) { // 단어 종료로 거꾸로 출력
                        sb.append(dq.pop());
                    }
                    sb.append(S.charAt(i));
                } else { // 공백이 아니면 스택에 쌓아주기
                    dq.push(S.charAt(i));
                }
            }
        }

        while (!dq.isEmpty()) { // 스택에 남은 단어를 거꾸로 출력
            sb.append(dq.pop());
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
