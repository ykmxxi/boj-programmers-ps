-- 2021년 가입한 전체 회원들 중 상품을 구매한 회원수, 상품을 구매한 회원의 비율 값을 년, 월 별로 출력
-- 회원의 비율은 소수점 두번째자리에서 반올림, 년을 기준 오름차순 정렬, 월을 기준 오름차순 정렬

select year(o.SALES_DATE) as YEAR, month(o.SALES_DATE) as MONTH, 
       count(distinct o.USER_ID) as PUCHASED_USERS, 
       round(count(distinct o.USER_ID) / (select count(USER_ID) 
                                      from USER_INFO 
                                      where YEAR(JOINED) = 2021), 1) as PUCHASED_RATIO
FROM USER_INFO as u inner join ONLINE_SALE as o on u.USER_ID = o.USER_ID
where year(u.JOINED) = 2021
group by YEAR, MONTH
order by YEAR, MONTH;
