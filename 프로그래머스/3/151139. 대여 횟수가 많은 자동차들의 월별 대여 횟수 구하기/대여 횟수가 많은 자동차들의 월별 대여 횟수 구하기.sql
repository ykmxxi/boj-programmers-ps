-- 대여 시작일 기준 2022-08 ~ 2022 10 까지 총 대여 횟수가 5회 이상인 자동차들
-- 해당 기간 동안의 월별(group by MONTH) 자동차 ID 별(group by CAR_ID) 총 대여 횟수 출력
-- 월 기준 오름차순, 자동차 ID 기준 내림차순
-- 특정 월의 총 대여 횟수가 0인 경우 제외

select month(START_DATE) MONTH, CAR_ID, count(*) RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where CAR_ID in (
    select CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where DATE_FORMAT(START_DATE, '%Y%m') between '202208' and '202210'
    group by CAR_ID
    having count(*) >= 5
    ) and date_format(START_DATE, '%Y%m') between '202208' and '202210'
group by MONTH, CAR_ID
having RECORDS > 0
order by MONTH, CAR_ID desc;
