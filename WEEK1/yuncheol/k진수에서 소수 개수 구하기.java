import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String customNumber = toCustomNumber((long) n, (long) k);
        String[] splitedArray = splitArray(customNumber);
        for (String str : splitedArray) {
            long number = Long.parseLong(str);
            if (isPrime(number)) {
                answer += 1;
            }
        }
        
        return answer;
    }
    
    private String[] splitArray(String str) {
        String[] splitedString = str.split("0");
        List<String> list = new ArrayList<>();
        
        for (String s : splitedString) {
            if (!s.equals("")) {
                list.add(s);
            }
        }
        
        return list.stream().toArray(String[]::new);
    }
    
    private String[] deleteEmptyString(String[] strArray) {
        List<String> list = new ArrayList<>();
        for (String str : strArray) {
            if (!str.equals("")) {
                list.add(str);
            }
        }
        
        return list.stream().toArray(String[]::new);
    }
    
    private String toCustomNumber(long n, long k) {
        String customNumber = "";
        
        while (true) {
            if (n / k != 0) {
                customNumber = Long.toString(n % k) + customNumber;
                n = n / k;
            } else {
                customNumber = Long.toString(n % k) + customNumber;
                break;
            }
        }
        
        return customNumber;
    }
    
    private boolean isPrime(long n) {
        if (n <= 1) { return false; }
        
        long sqrtN = (long) Math.floor(Math.sqrt(n));
        
        for (long i = 2; i <= sqrtN; i++) {
            if (n % i == 0) return false;
        }
        
        return true;
    }
}