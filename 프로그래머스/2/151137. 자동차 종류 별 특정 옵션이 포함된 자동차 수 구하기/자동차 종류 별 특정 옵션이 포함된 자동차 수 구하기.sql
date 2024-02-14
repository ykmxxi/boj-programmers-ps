-- 통풍, 열선, 가죽시트 중 하나 이상의 옵션이 포함된 자동차가 종류 별(group by CAR_TYPE)로 몇 대(count)
-- 자동차 종류 기준 오름차순

select CAR_TYPE, count(*) CARS
from CAR_RENTAL_COMPANY_CAR
where OPTIONS like '%시트%'
group by CAR_TYPE
order by CAR_TYPE;
