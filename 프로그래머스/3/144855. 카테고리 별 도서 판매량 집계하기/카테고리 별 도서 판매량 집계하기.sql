-- BOOK_ID(BOOK PK, BOOK_SALES FK)
-- 2022년 1월 카테고리 별(group by CATEGORY) 도서 판매량 합산(sum(SALES)) 출력
-- 카테고리 기준 오름차순
select b.CATEGORY, sum(bs.SALES) TOTAL_SALES
from BOOK b inner join BOOK_SALES bs on b.BOOK_ID = bs.BOOK_ID and date_format(bs.SALES_DATE, '%Y-%m') = '2022-01'
group by b.CATEGORY
order by b.CATEGORY asc;
