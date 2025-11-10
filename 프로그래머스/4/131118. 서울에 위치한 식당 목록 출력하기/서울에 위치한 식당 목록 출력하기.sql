-- 서울에 위치한 식당들의 ID, 이름, 종류, 즐겨찾기수, 주소, 리뷰 평균 점수 조회
-- 리뷰 평균점수: 소수점 세 번째 자리에서 반올림
-- 평균점수 내림차순, 즐겨찾기수 내림차순

SELECT ri.REST_ID, ri.REST_NAME, ri.FOOD_TYPE, ri.FAVORITES, ri.ADDRESS, ROUND(AVG(rr.REVIEW_SCORE), 2) AS SCORE
FROM REST_INFO ri INNER JOIN REST_REVIEW rr ON ri.REST_ID = rr.REST_ID
WHERE ri.ADDRESS LIKE '서울%'
GROUP BY ri.REST_ID
ORDER BY SCORE DESC, ri.FAVORITES DESC;
