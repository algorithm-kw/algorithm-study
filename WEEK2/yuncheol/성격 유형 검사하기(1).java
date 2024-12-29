import java.util.*;

// 점수가 같으면 사전순
// Map <String, Integer> 
// key : 성격 유형 세트
// value : 성격 유형에 대한 점수

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        int N = survey.length;
        
        Map<String, Integer> map = new HashMap<>();
        map.put("RT", 0);
        map.put("CF", 0);
        map.put("JM", 0);
        map.put("AN", 0);
        
        for (int i = 0; i < N; i++) {
            updateMap(map, survey[i], choices[i]);
        }
        
        for (String key: map.keySet()) {
            if (map.get(key) <= 0) {
                answer += Character.toString(key.charAt(0));
            } else {
                answer += Character.toString(key.charAt(1));
            }
        }
        
        return answer;
    }
    
    private void updateMap(Map<String, Integer> map, String survey, int score) {
        if (survey.charAt(1) - survey.charAt(0) < 0) {
            StringBuilder sb = new StringBuilder(survey);
            sb.reverse();
            map.put(sb.toString(), map.get(sb.toString()) + 4 - score);
        } else {
            map.put(survey, map.get(survey) + score - 4);
        }
    }
}