function solution(n, k) {
    var result = 0;
    let converted = convert(n, k); // n을 k진수로 변환
    let splitted = converted.split("0"); // 변환된 문자열을 0을 기준으로 분리

    for (element of splitted) {
        if(!element.length) continue; // 길이가 0인 문자열 걸러냄
        if (isPrimeNumber(parseInt(element))) result++; // 분리된 유효한 문자열을 검사한 후 정답 도출
    }

    return result;
}

// 0번째 인자를 1번째 인자의 진수로 변환하는 함수 (반환은 String)
function convert(number, to) {
    var result = "";
    var next = number;

    // number가 to로 몇 차 나눠지는지 구한 후 range 만들기
    let dimensions = Array.from(
        { length: getDimension(number, to) + 1 },
        (_, index) => index,
    );
    
    // reverse range
    dimensions.reverse();

    for (i of dimensions) {
        // 각 차수별로 to를 제곱한 수로 몫을 구해 이어붙임
        let quotient = parseInt(next / power(to, i));
        result = `${result}${quotient}`;

        next %= power(to, i);
    }

    return result;
}

// 전달된 인자가 소수인지 구하는 함수
function isPrimeNumber(number) {
    if (number < 2) return false;
    if (number === 2) return true;

    switch (number) {
        case 3, 5, 7:
            return true;
        default:
            break;
    }

    if (number % 2 === 0) return false;

    let toSqrt = parseInt(Math.floor(Math.sqrt(number)));
    let range = Array.from(
        { length: toSqrt - 1 },
        (_, index) => index+2,
    );

    for (i of range) {
        if (number % i === 0) return false;
    }

    return true;
}

// 0번째 인자가 1번째 인자로 몇번 나눠지는지 차수를 구하는 함수
function getDimension(number, k) {
    var result = 0;
    var unit = 1;

    while (true) {
        unit *= k;
        if (number < unit) break;
        result++;
    }

    return result;
}

// 0번째 인자의 k승 수를 구하는 함수
function power(number, k) {
    if (k === 0) return 1;
    return power(number, k - 1) * number;
}