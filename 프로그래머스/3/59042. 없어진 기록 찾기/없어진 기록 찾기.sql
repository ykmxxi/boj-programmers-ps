-- 입양을 간 기록이 있는데, 보호소에 들어온 기록이 없는 동물의 id, 이름
-- id 오름차순

select o.ANIMAL_ID, o.NAME
from ANIMAL_OUTS as o left join ANIMAL_INS as i on o.ANIMAL_ID = i.ANIMAL_ID
where i.ANIMAL_ID is null
order by ANIMAL_ID asc;
