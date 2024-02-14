-- 2022년 5월 예약한 환자 수를 진료과코드 별로 조회(group by MCDP_CD)
-- 예약한 환자 수 기준 오름차순, 진료과 코드 기준 오름차순
select MCDP_CD 진료과코드, count(APNT_NO) 5월예약건수
from APPOINTMENT
where date_format(APNT_YMD, '%Y-%m') = '2022-05'
group by MCDP_CD
order by 5월예약건수, 진료과코드;