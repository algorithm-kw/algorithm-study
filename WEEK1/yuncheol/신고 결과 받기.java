import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int id_count = id_list.length;
        int report_count = report.length;
        
        int[] answer = new int[id_count];
        
        Map<String, List<String>> report_map = new HashMap<>();
        Map<String, Integer> delete_map = new HashMap<>();
        Map<String, Integer> answer_map = new HashMap<>();
        
        report = Arrays.stream(report).distinct().toArray(String[]::new);
        
        for (int i = 0; i < id_count; i++) {
            report_map.put(id_list[i], new ArrayList<String>());
            delete_map.put(id_list[i], k);
            answer_map.put(id_list[i], 0);
        }
        
        for (String rpt: report) {
            String[] from_to = rpt.split(" ");
            String from = from_to[0];
            String to = from_to[1];
            
            report_map.get(from).add(to);
            delete_map.put(to, delete_map.get(to) - 1);
        }
        
        for (String key: answer_map.keySet()) {
            for (String report_value: report_map.get(key)) {
                if (delete_map.get(report_value) <= 0) {
                    answer_map.put(key, answer_map.get(key) + 1);
                }
            }
        }
        
        for (int i = 0; i < id_count; i++) {
            answer[i] = answer_map.get(id_list[i]);
        }
        
        return answer;
    }
}