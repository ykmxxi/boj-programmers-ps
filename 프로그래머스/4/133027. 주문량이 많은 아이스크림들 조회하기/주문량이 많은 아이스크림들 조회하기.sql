-- 상반기: FIRST_HALF, 7월: JULY
-- FIRST_HALF: PK(FLAVOR), JULY의 FK(SHIPMENT_ID)
-- JULYL: PK(SHIPMENT_ID), FIRST_HALF의 FK(FLAVOR)
-- 7월 아이스크림 총 주문량과 상반기 아이스크림의 총 주문량을 더해 값이 큰 순서대로 상위 3개 맛 조회

select f.FLAVOR
from FIRST_HALF as f inner join (
    select FLAVOR, sum(TOTAL_ORDER) TOTAL_ORDER
    from JULY
    group by FLAVOR) as j
using(FLAVOR)
order by (f.TOTAL_ORDER + j.TOTAL_ORDER) desc
limit 3;