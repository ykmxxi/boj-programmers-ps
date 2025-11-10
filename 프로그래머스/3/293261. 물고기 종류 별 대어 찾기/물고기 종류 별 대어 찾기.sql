-- 종류 별로 가장 큰 물고기 id, 이름, 길이
-- 물고기 id에 대한 오름차순

select fi.ID, fni.FISH_NAME, fi.LENGTH
from FISH_INFO fi join FISH_NAME_INFO fni on fi.FISH_TYPE = fni.FISH_TYPE
where (fi.FISH_TYPE, fi.LENGTH) in (
    select FISH_TYPE, max(LENGTH)
    from FISH_INFO
    group by FISH_TYPE
)
order by fi.id asc;
