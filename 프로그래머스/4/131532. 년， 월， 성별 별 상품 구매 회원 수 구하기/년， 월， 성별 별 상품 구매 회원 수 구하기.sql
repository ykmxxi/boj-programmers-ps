-- 0: 남자, 1: 여자
-- 년, 월, 성별 별로 상품을 구매한 회원수 집계
-- 년, 월, 성별을 기준으로 오름차순, 성별 정보가 없으면 제외

select year(os.SALES_DATE) YEAR, month(os.SALES_DATE) MONTH, ui.GENDER, count(distinct os.USER_ID) USERS
from ONLINE_SALE os inner join USER_INFO ui on os.USER_ID = ui.USER_ID
group by YEAR, MONTH, ui.GENDER
having ui.GENDER is not null
order by YEAR, MONTH, ui.GENDER;
