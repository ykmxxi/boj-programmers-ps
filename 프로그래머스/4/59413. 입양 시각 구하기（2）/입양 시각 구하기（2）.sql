-- 몇 시에 입양이 가장 활발하게 일어나는지 알아보려 한다.
-- 0시 부터 23시 까지, 각 시간대별로 입양이 몇 건 발생했는지 조회
-- 결과는 시간대 순으로 정렬

set @hour = -1;
select @hour := @hour + 1 HOUR,
    (select count(*)
     from ANIMAL_OUTS
     where hour(DATETIME) = @hour
    ) COUNT
from ANIMAL_OUTS
where @hour < 23
order by HOUR;
