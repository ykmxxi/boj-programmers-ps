/*  
    - 전화 키패드에서 왼손과 오른손의 엄지만 이용해서 숫자만을 입력하려고 함
    - 맨 처음 왼손 엄지: *, 오른쪽 엄지: # 위치에서 시작
    - 엄지 손가락 사용 규칙
        1. 상하좌우 4가지 방향 한 칸 이동(1)
        2. 왼쪽 열의 3개의 숫자 1, 4, 7 입력시 왼손만 사용
        3. 오른쪽 열의 3개의 숫자 3, 6, 9 입력시 오른손만 사용
        4. 가운데 열의 4개의 숫자 2, 5, 8, 0 입력시 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 손가락 사용
            4-1. 두 엄지손가락의 거리가 같으면 오른손잡이는 오른손, 왼손잡이는 왼손 사용
    - numbers: 누를 번호, hand: 손잡이("left" pr "right")
    - 각 번호를 누른 엄지손가락이 왼손인지 오른손인 지를 나타내는 연속된 문자열 형태를 return
*/

class Solution {
    static int lx, ly, rx, ry;
    
    static char check(int x, int y, String hand) {
        int lv = Math.abs(lx - x) + Math.abs(ly - y);
        int rv = Math.abs(rx - x) + Math.abs(ry - y);
        
        if (lv < rv) {
            lx = x;
            ly = y;
            return 'L';
        }
        if (lv > rv) {
            rx = x;
            ry = y;
            return 'R';
        }
        if (hand.equals("left")) {
            lx = x;
            ly = y;
            return 'L';
        }    
        rx = x;
        ry = y;
        return 'R';
    }
    
    static char process(int number, String hand) {
        if (number == 1 || number == 4 || number == 7) { // 왼쪽 열인 경우
            ly = 0;
            if (number == 1) {
                lx = 0;
            } else if (number == 4) {
                lx = 1;
            } else {
                lx = 2;
            }
            return 'L';
        } else if (number == 3 || number == 6 || number == 9) { // 오른쪽 열인 경우
            ry = 2;
            if (number == 3) {
                rx = 0;
            } else if (number == 6) {
                rx = 1;
            } else {
                rx = 2;
            }
            return 'R';
        }
            
        int x = 0;
        int y = 1;
        
        if (number == 2) {
            x = 0;
        } else if (number == 5) {
            x = 1;
        } else if (number == 8) {
            x = 2;
        } else {
            x = 3;   
        }
        return check(x, y, hand);
        
    }
    
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        int[] mid = {2, 5, 8, 0};
        
        lx = rx = 3;
        ly = 0;
        ry = 2;
        
        for (int number : numbers) {
            char ch = process(number, hand);
            sb.append(ch);
        }
        
        return sb.toString();
    }
}