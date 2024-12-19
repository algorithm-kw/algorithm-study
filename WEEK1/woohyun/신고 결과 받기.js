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
        // 신고 내역 리스트 
        let splitted = _report.split(" ");
        let reporter = splitted[0];
        let reportee = splitted[1];
        
        if(!reporteeMap[reporter].includes(reportee)) {
            reporteeMap[reporter].push(reportee);
        }
        
        if(!reporterMap[reportee].includes(reporter)) {
            reporterMap[reportee].push(reporter);
        }
    }
    
    for (id of id_list) {
        var count = 0;
        
        for (reportee of reporteeMap[id]) {
            if(reporterMap[reportee].length >= k) count++;
        }
        
        answer.push(count);
    }
    
    return answer;
}