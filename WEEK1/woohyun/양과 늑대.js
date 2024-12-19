var maxCount = 1; // 양 최대 합
var infoGlobal; // info 전역 변수 선언
var ways = []; // 트리 루트 전역 변수 선언

function solution(info, edges) {
    // infoGlobal 전역 변수 초기화
    infoGlobal = [...info];
    
    // 이차원 배열로 트리 루트 초기화
    for (var i=0; i<info.length; i++) ways[i] = [];
    for (edge in edges) ways[edge[0]].push(edge[1]);
    
    // 자식 노드를 비교해서 양이 있는 노드를 왼쪽 (우선 탐색 지점) 으로 옮김
    for (index in ways) {
        // 자식 노드가 2개인 경우만 탐색
        if(ways[index].length < 2) continue;
        
        // 왼쪽이 늑대 (1), 오른쪽이 양 (0)인 경우 스왑
        if (info[ways[index][0]] > info[ways[index][1]]) {
            var temp = ways[index][0];
            ways[index][0] = ways[index][1];
            ways[index][1] = temp;
        }  
    }
    
    // 0번 노드부터 시작해 탐색 시작
    for(index in ways[0]) goNextNode(0, 0, [0], 0);
    
    return maxCount;
}

function goNextNode(sheep, wolves, nextWays, wayIndex) {
    // 정해진 노드로 방문
    var currentNode = nextWays[wayIndex];
    
    // 양인지 늑대인지 판단해서 동물 수 업데이트
    var nextSheep = sheep;
    var nextWolves = wolves;
    
    infoGlobal[currentNode] === 0 ? nextSheep++ : nextWolves++;
    
    // sum이 0이면 늑대와 양의 수가 같아지는 지점이므로 재귀문 탈출
    if (nextSheep === nextWolves) return;
    
    maxCount = maxCount < nextSheep ? nextSheep : maxCount;
    
    // nextWays 업데이트
    // 1. nextWays swallow copy
    var nextWaysCopy = [...nextWays];
    // 2. 현재 wayIndex의 원소 노드를 그 자식 노드로 대체
    nextWaysCopy.splice(wayIndex, 1, ...ways[currentNode]);

    // sum이 0보다 크다면 양이 더 많다는 것이므로 다음 노드로 재귀 진행
    for (index in nextWaysCopy) {
        goNextNode(nextSheep, nextWolves, nextWaysCopy, index);
    }
}