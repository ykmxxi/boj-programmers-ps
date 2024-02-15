-- 고양이와 개가 각각 몇마리인지 조회
-- 고양이를 먼저 조회

select ANIMAL_TYPE, count(*) count
from ANIMAL_INS
group by ANIMAL_TYPE
order by ANIMAL_TYPE;