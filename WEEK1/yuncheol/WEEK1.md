### 신고 결과 받기

1. 중복 제거

   ```java
   Arrays.stream(array).distinct().toArray(String[]:new);
   ```

2. 3 Case Map(Dictionary)

   - 유저(String) - 신고한 목록(List) / report_map
   - 유저(String) - 신고당한 횟수(Integer) / delete_map
   - 유저(String) - 처리결과 횟수(Integer) / answer_map

3. 정지 유저 구하기
   1. 3 Case Map 초기화
   2. update delete_map
   3. update answer_map
   4. mapping answer_map & answer

### k진수에서 소수 개수 구하기

1. 학습 내용

   ```java
   // 소수 판별

   // 진법 변경

   // split (-> 문제 요구사항을 통해 파악)
   ```

2. 이후 고민해볼 사항

   - 내가 처음 시도한 방식과 split 비교 (왜 내 방식이 틀렸는가?)

### 주차 요금 계산

1. 학습 내용

   keySet() 내부에서 Map의 remove method를 호출하면 동시성 오류 발생

   ```java
   for (String key: map.keySet()) {
      map.remove(key);
   }
   ```

2. 다음 문제에서 시도해볼 사항

   - 코드 작성하기 전에 자료구조 A4에 적어두기
   - 함수화 하기
   - 변수명 조금 더 신경쓰기

### 문제 1, 2을 풀며 ...

문제를 구조화 시키지 않고 풀이에 들어가면 중간에 길을 잃어 시간이 오래걸리는 경우가 생긴다. A4용지를 사용해 코드를 작성하기 전에 필요한 대략의 자료구조를 구조화해보자. 어떤 계산을 할때 어떤 자료구조가 필요한지... 예를들어, Map<String, String>, int[] 등등

머릿 속에 함수화해야겠다는 생각이들면 귀찮더라도 꼭 함수화하는 습관을 길러야된다. 그렇지 않으면, 코드가 지저분해 중간에 수정하기가 어렵다.

1, 2를 풀며 고민했던 내용을 3에 적용시키니 문제 풀이가 한결 깔끔해졌다.
