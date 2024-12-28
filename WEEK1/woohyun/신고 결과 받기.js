function solution(id_list, report, k) {
    var answer = [];
    
    var reporteeMap = {};   // key: 신고한 유저, value: 신고당한 유저 리스트
    var reporterMap = {};   // key: 신고당한 유저, value: 신고한 유저 리스트
    
    // 두 맵 모두 초기화
    for (id of id_list) { 
        reporteeMap[id] = [];
        reporterMap[id] = [];
    }
    

    for (_report of report) {
        // 신고 내역 리스트 split (신고자-피신고자)
        let splitted = _report.split(" ");
        let reporter = splitted[0];
        let reportee = splitted[1];
        
        // 신고자 맵에 중복 검사 후 저장
        if(!reporteeMap[reporter].includes(reportee)) {
            reporteeMap[reporter].push(reportee);
        }
        
        // 피신고자 맵에 중복 검사 후 저장
        if(!reporterMap[reportee].includes(reporter)) {
            reporterMap[reportee].push(reporter);
        }
    }
    
    // 신고자 맵에서 피신고자 리스트를 탐색한 뒤 신고당한 횟수 비교 후 정답 출력
    for (id of id_list) {
        var count = 0;
        
        for (reportee of reporteeMap[id]) {
            if(reporterMap[reportee].length >= k) count++;
        }
        
        answer.push(count);
    }
    
    return answer;
}