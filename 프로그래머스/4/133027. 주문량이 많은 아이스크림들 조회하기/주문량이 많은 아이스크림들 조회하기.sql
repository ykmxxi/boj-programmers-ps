# 7월 아이스크림 총 주문량, 상반기 아이스크림 총 주문량 더한 값 큰 순서대로 상위 3개

select fh.FLAVOR
from FIRST_HALF as fh join (
    select FLAVOR, sum(TOTAL_ORDER) as total
    from JULY
    group by FLAVOR
) as j on fh.FLAVOR = j.FLAVOR
order by (fh.TOTAL_ORDER + j.total) desc
limit 3;
