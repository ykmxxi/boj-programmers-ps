-- 2022년 10월 16일 대여 중인 자동차 -> 대여중 표시
-- 대여중이지 않은 자동차는 대여 가능을 표시하는 AVAILABILITY 추가
-- 반납 날짜가 2022년 10월 16일인 경우도 대여중
-- 자동차 ID 기준 내림차순

select CAR_ID,
    (case when car_id in (
        select CAR_ID
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where '2022-10-16' between date_format(START_DATE, '%Y-%m-%d') and date_format(END_DATE, '%Y-%m-%d')
    )
    then '대여중' else '대여 가능' end
) AVAILABILITY
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by CAR_ID
order by CAR_ID desc;