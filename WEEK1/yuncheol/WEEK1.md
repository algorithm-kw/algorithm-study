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
