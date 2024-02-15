-- 09:00 ~ 19:59  시간대별로 입양이 몇 건 발생
-- 시간대 순으로 정렬

select hour(DATETIME) HOUR, count(*) COUNT
from ANIMAL_OUTS
where hour(DATETIME) between 9 and 19
group by HOUR
order by HOUR;
