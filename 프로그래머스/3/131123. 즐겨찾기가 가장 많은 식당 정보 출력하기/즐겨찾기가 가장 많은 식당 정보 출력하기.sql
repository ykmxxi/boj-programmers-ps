-- REST_INFO 테이블에서 음식 종류별로(그룹화) 즐겨찾기가 가장 많은 식당(MAX) 음식 죵류, ID, 식당 이름, 즐겨찾기수 조회
-- 음식 종류를 기준으로 내림차순 정렬

select FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
from REST_INFO
where (FOOD_TYPE, FAVORITES) in (
    select FOOD_TYPE, MAX(FAVORITES)
    from REST_INFO
    group by FOOD_TYPE
)
order by FOOD_TYPE desc
