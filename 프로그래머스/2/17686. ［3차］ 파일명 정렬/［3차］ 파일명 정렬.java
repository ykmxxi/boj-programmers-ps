/*
    - 이름 순으로 정렬하면 나중에 만들어진 ver10이 ver9보다 먼저옴
    - 숫자가 포함된 파일 목록은 불편
    - 파일명은 크게 HEAD NUMBER TAIL 세 부분
        - head: 문자로만 이루어지고 최소한 한 글자 이상
        - number: 앞쪽에 0 가능 0 ~ 99999 사이의 숫자로 (00000 or 0101 가능)
        - tail: 나머지 부분으로 숫자나 문자 모두 가능하고 빈 문자열도 가능
    - foo9.txt = HEAD(foo), NUMBER(9), TAIL(.txt)
    - foo010bar020.zip = HEAD(foo), NUMBER(010), TAIL(bar020.zip)
    - HEAD 부분 사전 순 정렬(오름차순, 대소문자 구분 x) -> number 숫자순 오름차순 정렬(9, 10, 0011, 012, 13, 014) (012와 12는 같음)
    - head number 모두 순서가 같으면 입력에 주어진 순서를 유지
*/

import java.util.*;

class Solution {
    
    public String[] solution(String[] files) {
        List<Info> list = new ArrayList<>();
        
        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            
            int numberStart = 0;
            int tailStart = 0;
            int cnt = 0;
            boolean first = true;
            
            for (int j = 0; j < file.length(); j++) {
                if (first) {
                    if (Character.isDigit(file.charAt(j))) {
                        numberStart = j;
                        cnt++;
                        first = false;
                    }
                } else {
                    if (Character.isDigit(file.charAt(j))) {
                        if (cnt < 5) {
                            cnt++;
                        } else {
                            tailStart = j;
                            break;
                        }
                    } else {
                        tailStart = j;
                        break;
                    }
                }
            }
            
            String head = file.substring(0, numberStart);
            if (tailStart == 0) { // 꼬리가 없음
                tailStart = file.length();
            }
            String number = file.substring(numberStart, tailStart);
            String tail = file.substring(tailStart);
            
            list.add(new Info(head, number, tail, i));
            
        }
        
        Collections.sort(list);
        return list.stream().map(Info::convert).toArray(String[]::new);
    }
    
    static class Info implements Comparable<Info> {
        String head;
        String number;
        String tail;
        int index;
        
        Info (String head, String number, String tail, int index) {
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.index = index;
        }
        
        String convert() {
            return head + number + tail;
        }
        
        @Override
        public int compareTo(Info o) {
            int first = this.head.toLowerCase().compareTo(o.head.toLowerCase());
            if (first != 0) {
                return first;
            }
            
            int second = Integer.parseInt(this.number) - Integer.parseInt(o.number);
            if (second != 0) {
                return second;
            }
            return this.index - o.index;
        }
    }
    
}