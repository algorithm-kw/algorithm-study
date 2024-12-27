class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int R = board.length;
        int C = board[0].length;
        int[][] prefixSum = new int[R+1][C+1];
        
        for (int[] element: skill) {
            int type = element[0];
            int r1 = element[1];
            int c1 = element[2];
            int r2 = element[3];
            int c2 = element[4];
            int degree = element[5];
            
            action(type, r1, c1, r2, c2, degree, prefixSum);
        }
        
        // prefix sum (left to right)
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                prefixSum[r][c+1] += prefixSum[r][c];
            }
        }
        
        // prefix sum (top to bottom)
        for (int c = 0; c < C; c++) {
            for (int r = 0; r < R; r++) {
                prefixSum[r+1][c] += prefixSum[r][c];
            }
        }
        
        // board + prefix sum
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                board[r][c] += prefixSum[r][c];
                
                if (board[r][c] > 0) {
                    answer += 1;
                }
            }
        }
        
        return answer;
    }
    
    private void action(int type, int r1, int c1, int r2, int c2, int degree, int[][] prefixSum) {
        if (type == 1) {
            type = -1;
        } else if (type == 2) {
            type = 1;
        }
        
        prefixSum[r1][c1] += degree * type;
        prefixSum[r1][c2+1] += (-1) * degree * type;
        prefixSum[r2+1][c1] += (-1) * degree * type;
        prefixSum[r2+1][c2+1] += degree * type;
    }
}