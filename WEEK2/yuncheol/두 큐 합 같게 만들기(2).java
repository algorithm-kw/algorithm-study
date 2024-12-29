import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int limitCount = queue1.length + queue2.length;
        long sum = 0;
        long sumL = 0;
        long sumR = 0;
        
        Queue<Long> queueL = new LinkedList<>();
        Queue<Long> queueR = new LinkedList<>();
        
        for (long i: queue1) {
            queueL.add(i);
            sum += i;
            sumL += i;
        }
        
        for (long i: queue2) {
            queueR.add(i);
            sum += i;
            sumR += i;
        }
        
        if (sum % 2 != 0) {
            return -1;
        }
        
        int answer = recursive(queueL, queueR, sumL, sumR, sum / 2, 0, limitCount);
        
        return answer;
    }
    
    private int recursive(Queue<Long> queueL, Queue<Long> queueR, long sumL, long sumR, long target, int count, int limitCount) {
        
        while (true) {
            if (sumL == target && sumR == target) {
                return count;
            }

            if (count > limitCount * 2) {
                return -1;
            }
            
            if (sumL < sumR) {
                long element = queueR.poll();
                sumR -= element;
                queueL.add(element);
                sumL += element;
            } else if (sumL > sumR) {
                long element = queueL.poll();
                sumL -= element;
                queueR.add(element);
                sumR += element;
            }
            
            count += 1;
        }       
        
    }
}