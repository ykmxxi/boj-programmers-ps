-- FOOD_PRODUCT 식품분류별로(group by CATEGORY) 제일 비싼 식품의 분류, 가격, 이름 조회
-- 분류가 과자, 국, 김치, 식용유인 경우만 출력
-- 식품 가격 기준 내림차순

select CATEGORY, PRICE MAX_PRICE, PRODUCT_NAME
from FOOD_PRODUCT
where CATEGORY in ('식용유', '과자', '국', '김치') and
    PRICE in (
        select MAX(PRICE)
        from FOOD_PRODUCT
        group by CATEGORY
    )
order by MAX_PRICE desc;