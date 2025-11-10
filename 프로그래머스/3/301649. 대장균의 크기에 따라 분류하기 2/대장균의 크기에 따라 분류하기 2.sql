# 크기를 내림차순 정렬, 상위 0~25 CRITICAL, 26 ~ 50 HIGH, 51 ~ 75 MEDIUM, 76 ~ 100 LOW
# id 오름차순

select T.ID,
    case
        when T.rk <= T.total_count * 0.25 THEN 'CRITICAL'
        when T.rk <= T.total_count * 0.50 THEN 'HIGH'
        when T.rk <= T.total_count * 0.75 THEN 'MEDIUM'
        else 'LOW'
    end as COLONY_NAME
from (
    select ID, rank() over (order by SIZE_OF_COLONY desc) as rk, count(*) over () as total_count
    from ECOLI_DATA
) as T
order by T.ID asc;
