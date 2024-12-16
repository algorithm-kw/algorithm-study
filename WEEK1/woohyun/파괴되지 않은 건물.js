// 나의 풀이 (정확성 10/10 통과, 효율성 0/7 통과)
function solution(board, skill) {
    var answer = 0;
    
    for (eachSkill of skill) {
        for (var row = eachSkill[1]; row<=eachSkill[3]; row++) {
            for(var col = eachSkill[2]; col <= eachSkill[4]; col++) {
                board[row][col] += eachSkill[5] * (eachSkill[0] == 1 ? -1 : 1);
            }
        }
    }
    
    for (row of board) {
        for(element of row) {
            if(element > 0) answer++;
        }
    }
    
    return answer;
}

// 효율성 풀이 모범 답안 (누적합 개념 사용)

var damages; // 건물에 가해진 데미지의 총합만 구하는 행렬 (누적합을 위해 board 배열보다 가로, 세로의 길이가 1만큼 길다)

function solution(board, skill) {
    var answer = 0;
    
    // damages 배열 전부 0으로 초기화
    damages = Array.from(
        Array(board.length + 1),
        (_) => Array(board[0].length+1).fill(0),
    );
    
    // damages 배열에 누적합을 위한 모서리 초기화
    skill.forEach((eachSkill) => {
        const [type, r1, c1, r2, c2, degree] = eachSkill;
        const damage = degree * (type === 1 ? -1 : 1);
        
        damages[r1][c1] += damage;
        damages[r1][c2+1] += -damage;
        damages[r2+1][c1] += -damage;
        damages[r2+1][c2+1] += damage;
    });
    
    // damages 가로합 (오른쪽으로)
    for(row of damages) {
        for (var col=1; col < row.length; col++) {
            row[col] += row[col-1];
        }
    }
    
    // damages 세로합 (아래로)
    for(var row=1; row < damages.length; row++) {
        for (var col=0; col < damages[row].length; col++) {
            damages[row][col] += damages[row-1][col];
        }
    }
    
    // board의 크기만큼 damages와 비교하여 정답 도출
    for(var row=0; row < board.length; row++) {
        for (var col=0; col < board[row].length; col++) {
            const density = board[row][col];
            const damage = damages[row][col];
            
            if(density + damage > 0) answer++;
        }
    }
    
    return answer;
}