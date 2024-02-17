-- 자동차 종류가 세단 or SUV 자동차 중 2022-11-01 ~ 2022-11-30 대여 가능하고, 30일 대여 금액이 50만 ~ 200만 미만
-- 자동차 ID, 종류, 대여 금액(FEE)
-- 대여금액 내림차순, 자동차 종류 오름차순, 자동차 ID 내림차순

select c1.CAR_ID, c1.CAR_TYPE, round(c1.DAILY_FEE * 30 * (100 - c3.DISCOUNT_RATE) / 100) as FEE
from CAR_RENTAL_COMPANY_CAR as c1
inner join CAR_RENTAL_COMPANY_RENTAL_HISTORY as c2 on c1.CAR_ID = c2.CAR_ID
inner join CAR_RENTAL_COMPANY_DISCOUNT_PLAN as c3 on c1.CAR_TYPE = c3.CAR_TYPE
where c1.CAR_ID not in (
    select CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where END_DATE >= '2022-11-01' AND START_DATE < '2022-12-01'
) and c3.DURATION_TYPE = '30일 이상'
group by c1.CAR_ID
having c1.CAR_TYPE in ('세단', 'SUV') and (FEE >= 500000 and FEE < 2000000)
order by FEE desc, CAR_TYPE, CAR_ID desc;
