-- 생산일자가 2022-05 식품들의 id, 이름, 총매출 조회
-- 총매출 기준 내림차순, 식품 id 기준 오름차순

select o.PRODUCT_ID, p.PRODUCT_NAME, sum(p.PRICE * o.AMOUNT) as TOTAl_SALES
from FOOD_ORDER as o inner join FOOD_PRODUCT as p on o.PRODUCT_ID = p.PRODUCT_ID
where year(o.PRODUCE_DATE) = 2022 and month(o.PRODUCE_DATE) = 5
group by PRODUCT_ID
order by TOTAL_SALES desc, PRODUCT_ID;
