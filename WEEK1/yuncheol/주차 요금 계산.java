import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, String> parkedCars = new HashMap<>();   // car number : (in) time
        Map<String, Integer> totalParkedTime = new TreeMap<>();
        
        for (String record: records) {
            String[] recordElement = record.split(" ");
            String time = recordElement[0];  // in or out time
            String carNumber = recordElement[1];
            String parkingType = recordElement[2];
            
            if (parkingType.equals("IN")) {
                parkedCars.put(carNumber, time);    // time is in time
            } else if (parkingType.equals("OUT")) {
                String parkInTime = parkedCars.get(carNumber);
                int parkedTime = timeToSecond(parkInTime, time);    // time is out time
                addTimeToCar(totalParkedTime, carNumber, parkedTime);   // total parked time += parked time
                parkedCars.remove(carNumber);
            }
        }
        
        for (String key: parkedCars.keySet()) {
            String parkInTime = parkedCars.get(key);
            int parkedTime = timeToSecond(parkInTime, "23:59");
            addTimeToCar(totalParkedTime, key, parkedTime);
            // parkedCars.remove(key);
        }
        
        int[] answer = new int[totalParkedTime.size()];
        int i = 0;
        for (String key: totalParkedTime.keySet()) {
            answer[i++] = calculateFees(totalParkedTime.get(key), fees);
        }
        
        return answer;
    }
    
    private int timeToSecond(String startTime, String endTime) {
        String[] endTimeArray = endTime.split(":");
        String[] startTimeArray = startTime.split(":");
        
        int endMinute = Integer.parseInt(endTimeArray[0]);
        int endSecond = Integer.parseInt(endTimeArray[1]);
        int startMinute = Integer.parseInt(startTimeArray[0]);
        int startSecond = Integer.parseInt(startTimeArray[1]);
        
        int parkingSecond = (endMinute * 60) + (endSecond) - (startMinute * 60) - (startSecond);
        
        return parkingSecond;
    }
    
    private void addTimeToCar(Map<String, Integer> totalParkedTime, String carNumber, int time) {
        if (totalParkedTime.containsKey(carNumber)) {
            totalParkedTime.put(carNumber, totalParkedTime.get(carNumber) + time);
        } else {
            totalParkedTime.put(carNumber, time);
        }
    }
    
    private int calculateFees(int totalTime, int[] fees) {
        // 기본 시간, 기본 요금, 단위 시간, 단위 요금
        int basicTime = fees[0];
        int basicFee = fees[1];
        int intervalTime = fees[2];
        int intervalFee = fees[3];
        int fee = 0;
        
        if (totalTime - basicTime <= 0) {
            return basicFee;
        } else {
            totalTime -= basicTime;
            fee += basicFee;
        }
        
        int quotient = totalTime / intervalTime;
        int remainder = totalTime % intervalTime;
        
        if (remainder == 0) {
            fee += intervalFee * quotient;
        } else {
            fee += intervalFee * (quotient + 1);
        }
        
        return fee;
    }
}