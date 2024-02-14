-- 상반기 동안 각 아이스크림 성분 타입(group by INGREDIENT_TYPE) 과 총주문량
-- 총주문량 기준 오름차순

select ii.INGREDIENT_TYPE, sum(fh.TOTAL_ORDER) TOTAL_ORDER
from FIRST_HALF as fh inner join ICECREAM_INFO as ii on fh.FLAVOR = ii.FLAVOR
group by ii.INGREDIENT_TYPE
order by TOTAL_ORDER;