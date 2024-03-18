class Solution {
    
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        String[] arr = {"4", "1", "2"};
        
        while(n > 0) {
            int tmp = n % 3;
            n /= 3;
            
            if(tmp == 0) {
                n--;
            }
            
            sb.append(arr[tmp]);
        }

        return sb.reverse().toString();
    }
    
}
