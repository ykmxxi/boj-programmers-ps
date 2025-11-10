# 사원별 성과금 정보를 조회, 점수별 등급과 등급에 따른 성과금 정보
# 96 이상:S (20%) / 90 이상: A(15%) / 80 이상: B(10%) / 이외 C (0%)

select he.EMP_NO, he.EMP_NAME, g.GRADE, case
    when g.GRADE = 'S' then SAL * 0.2
    when g.GRADE = 'A' then SAL * 0.15
    when g.GRADE = 'B' then SAL * 0.1
    else 0
    end as BONUS
from HR_EMPLOYEES he join (
    select EMP_NO, case
            when avg(SCORE) >= 96 then 'S'
            when avg(SCORE) >= 90 then 'A'
            when avg(SCORE) >= 80 then 'B'
            else 'C'
        end as GRADE
    from HR_GRADE
    group by EMP_NO
) as g on he.EMP_NO = g.EMP_NO
order by he.EMP_NO asc;
