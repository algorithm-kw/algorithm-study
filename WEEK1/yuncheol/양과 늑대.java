// * 양과 늑대 *
// edges -> graph
// 어떤 순서(방식)로 탐색을 진행할 것인가?
// 언제 snapshot?
// visited에 대한 처리 -> X

import java.util.*;

class Solution {
    private static int maxSheepCount = 0;
    private static int[][] edgesGraph;
    
    public int solution(int[] info, int[][] edges) {
        int N = info.length;
        edgesGraph = new int[N][N];
        for (int i = 0; i < edges.length; i++) {
            int r = edges[i][0];
            int c = edges[i][1];
            
            edgesGraph[r][c] = 1;
        }
        
        List<Integer> startNodes = new ArrayList<>();
        startNodes.add(0);
        
        recursive(info, 0, 0, 0, startNodes);
        
        return maxSheepCount;
    }
    
    public void recursive(int[] info, int sheepCount, int wolfCount, int curNode, List<Integer> nodes) {
        if (info[curNode] == 0) {
            sheepCount += 1;
        } else {
            wolfCount += 1;
        }
        
        if (sheepCount <= wolfCount) {
            return;
        }
        
        maxSheepCount = Math.max(maxSheepCount, sheepCount);
        
        List<Integer> next = new ArrayList<>(nodes);
        for (int i = 0; i < info.length; i++) {
            if (edgesGraph[curNode][i] == 1) {
                next.add(i);
            }
        }
        next.remove(Integer.valueOf(curNode));
        
        for (int n: next) {
            // if (info[curNode] == 0) {
            //     recursive(info, sheepCount + 1, wolfCount, n, next);
            // } else if (sheepCount > wolfCount + 1) {
            //     recursive(info, sheepCount, wolfCount + 1, n, next);
            // }
            
            recursive(info, sheepCount, wolfCount, n, next);
        }        
    }
}