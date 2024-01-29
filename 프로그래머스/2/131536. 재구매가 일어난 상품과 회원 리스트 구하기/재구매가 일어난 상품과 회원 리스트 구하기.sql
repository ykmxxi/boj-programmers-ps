-- ONLINE_SALE 테이블에서 동일한 회원이 동일한 상품을 재구매한 데이터를 구하여, 재구매한 회원 ID, 재구매한 상품 ID 출력
-- 회원 ID 오름차순, 상품 ID 내림차순

select USER_ID, PRODUCT_ID
from ONLINE_SALE
group by USER_ID, PRODUCT_ID
having count(*) >= 2
order by USER_ID asc, PRODUCT_ID desc;