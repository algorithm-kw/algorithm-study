function solution(fees, records) {
    var recordMap = new Map();

    for (record of records) {
        const splitted = record.split(" ");

        const isOut = splitted[2] == "OUT"; // 입차, 출차 여부
        const carNumber = splitted[1]; // Int 형의 차량 번호

        const hhMm = splitted[0].split(":");
        // 분으로 전환 (입차는 -, 출차는 +): 입차가 마지막인 경우는 음수로 표기해서 뒤에 23:59 더함
        const timeToMinutes = (parseInt(hhMm[0]) * 60 + parseInt(hhMm[1])) * (isOut ? 1 : -1);
        
        if (recordMap.get(carNumber)) recordMap.set(carNumber, recordMap.get(carNumber) + timeToMinutes);
        else recordMap.set(carNumber, timeToMinutes);
    }

    for (key of Array.from(recordMap.keys(), (e) => e)) {
        const minutes = recordMap.get(key);
        
        if (minutes <= 0) recordMap.set(key, minutes + 1439); // 23:59분 출차
    }
    
    const feeMap = [...recordMap.entries()].map((entry) => {
        return [parseInt(entry[0]), getParkingFee(entry[1], fees[0], fees[1], fees[2], fees[3])];
    });
    
    return [...feeMap.entries()]
    .sort((previous, next) => previous[1][0] - next[1][0])
    .map((entry) => entry[1][1]);
}

function getParkingFee(minutes, baseTime, baseFee, unitTime, unitFee) {
    if (minutes <= baseTime) return baseFee;
    return baseFee + Math.ceil((minutes - baseTime) / unitTime) * unitFee;
}