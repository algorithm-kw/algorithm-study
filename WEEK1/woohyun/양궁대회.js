var apeachScoreCopy = []; // 어피치 점수 복사
var ryanStart = []; // 라이언 점수

var maxScoreDiff = 0; // 최대 점수차
var result = []; // 결과

function solution(n, info) {
    apeachScoreCopy = [...info];
    for (score of info) ryanStart.push(score + 1);

    shoot(n, [], true); // 10점 스킵
    shoot(n, [], false); // 10점 획득

    if (result.length == 0) return [-1];
    return result;
}

function shoot(spare, previous, skip) {
    let willShot = ryanStart[previous.length] > spare ? spare : ryanStart[previous.length];

    // 이번에 쏠 (수 있는) 화살
    let shot = skip ? 0 : willShot;  // 이번에 쏜 화살
    let n = spare - shot; // 이번에 쏘고 남은 화살
    var current = [...previous]; // 이전 점수 배열
    current.push(shot); // 점수 배열에 쏜 화살 추가

    let currentCount = current.length;

    if (currentCount === 10) {
        current.push(n);
        return countResult(current);
    }

    if (n === 0) {
        for (_ in Array.from({ length: 11 - currentCount })) current.push(0);
        return countResult(current);
    }

    shoot(n, current, true);
    shoot(n, current, false);
}

function countResult(ryanScore) {
    var ryan = 0;
    var apeach = 0;

    for (i in Array.from({ length: 11 })) {
        if (ryanScore[i] - ryanStart[i] < 0) {
            if (apeachScoreCopy[i] > 0) apeach += 10 - i;
            continue;
        }

        ryan += 10 - i;
    }

    // ryan 패배
    if (apeach >= ryan) return;

    // 점수차가 지금까지보다 최대가 아닐 경우
    if (maxScoreDiff > ryan - apeach) return;

    // 점수차의 최대값과 동률
    if (maxScoreDiff == ryan - apeach) {
        for (i in Array.from({ length: 11 })) {
            if (ryanScore[10 - i] < result[10 - i]) return;
            if (ryanScore[10 - i] === result[10 - i]) continue;
            return result = ryanScore;
        }
    }

    // 점수차 최대값 갱신
    maxScoreDiff = ryan - apeach;
    result = ryanScore;
}

function scoreSum(scores) {
    return scores.reduce(
        (previous, current) => previous + current,
        0,
    );
}