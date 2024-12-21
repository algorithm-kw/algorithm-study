import java.util.*;

class Solution {
    private static int answer = 0;
    private static int[] answerArray = new int[11];
    public int[] solution(int n, int[] info) {
        int[] lionInfo = new int[11];
        dfs(info, lionInfo, 0, n, 0);
        
        if (answer == 0) {
            int[] errorArray = { -1 };
            return errorArray;
        }
        
        return answerArray;
    }
    
    private void dfs(int[] apeachInfo, int[] lionInfo, int arrowCount, int n, int index) {
        
        if (arrowCount > n) { return; }
        if (arrowCount == n) {
            updateAnswer(apeachInfo, lionInfo);
            return;
        }
        
        if (index == 11) {
            if (arrowCount < n) {
                lionInfo[10] += n - arrowCount;  // arrowCount가 부족한 만큼 라이언 과녁(lionInfo)에 채우기
                updateAnswer(apeachInfo, lionInfo);
                lionInfo[10] -= n - arrowCount;
            }
            
            return;
        }
        
        lionInfo[index] = apeachInfo[index] + 1;
        arrowCount += lionInfo[index];
        dfs(apeachInfo, lionInfo, arrowCount, n, index + 1);
        
        arrowCount -= lionInfo[index];
        lionInfo[index] = 0;
        dfs(apeachInfo, lionInfo, arrowCount, n, index + 1);
    }
    
    private int compareScore(int[] apeachInfo, int[] lionInfo) {
        int apeachScore = 0;
        int lionScore = 0;
        for (int i = 0; i < 11; i++) {
            if (lionInfo[i] != 0) {
                lionScore += (10 - i);
            } else {
                if (apeachInfo[i] > 0) {
                    apeachScore += (10 - i);
                }
            }
        }
        
        return lionScore - apeachScore;
    }
    
    private void deepCopyToAnswerArray(int[] lionInfo) {
        for (int i = 0; i < 11; i++) {
            answerArray[i] = lionInfo[i];
        }
    }
    
    private void updateAnswer(int[] apeachInfo, int[] lionInfo) {
        int point = compareScore(apeachInfo, lionInfo);  // 어피치와 라이언의 과녁(info)을 비교해서 point 얻기 
        if (point == answer) {  // 최대점수차(answer)과 point가 같을 때, 가장 낮은 점수를 맞힌 개수가 많은 경우로 최대점수차(answer) 변경(or 유지)
            for (int i = 10; i >= 0; i--) {
                if (answerArray[i] > lionInfo[i]) {
                    break;
                } else if (answerArray[i] < lionInfo[i]) {
                    deepCopyToAnswerArray(lionInfo);
                    break;
                }
            }
        }

        if (point > answer) {
            answer = point;
            deepCopyToAnswerArray(lionInfo);
        }
    }
}