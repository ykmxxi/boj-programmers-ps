# 종류: 세단, SUV -> 2022-11-01 ~ 2022-11-30 대여 가능 AND 30일간 대여 금액 50만원 이상 200만원 미만
-- id, 종류, 대여 금액(FEE)
-- 대여 금액 내림차순, 종류 오름 차순, id 내림차순

select c1.CAR_ID, c1.CAR_TYPE, floor(c1.DAILY_FEE * 30 * (100 - c3.DISCOUNT_RATE) / 100) FEE
from CAR_RENTAL_COMPANY_CAR as c1
join CAR_RENTAL_COMPANY_RENTAL_HISTORY as c2 on c1.CAR_ID = c2.CAR_ID
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN as c3 on c1.CAR_TYPE = c3.CAR_TYPE
where c1.CAR_ID not in (
    select CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where START_DATE <= '2022-11-30' and END_DATE >= '2022-11-01'
) and c3.DURATION_TYPE = '30일 이상'
group by c1.CAR_ID
having c1.CAR_TYPE in ('세단', 'SUV') and (FEE >= 500000 and FEE < 2000000)
order by FEE desc, CAR_TYPE asc, CAR_ID desc; 