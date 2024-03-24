-- 상품코드 별(group by PRODUCT_CODE) 매출액 합계 출력
-- 매축액 합계 기준 내림차순, 상품코드 기준 오름차순

select p.PRODUCT_CODE, sum(p.PRICE * o.SALES_AMOUNT) as SALES
from PRODUCT as p inner join OFFLINE_SALE as o on p.PRODUCT_ID = o.PRODUCT_ID
group by p.PRODUCT_CODE
order by SALES desc, p.PRODUCT_CODE;
