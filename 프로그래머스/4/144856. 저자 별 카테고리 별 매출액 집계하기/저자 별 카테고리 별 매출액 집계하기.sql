-- author_id -> BOOK(FK), AUTHOR(PK)
-- 2022년 1월 도서 판매 데이터 기준, 저자 별(group by 저자), 카테고리 별(group by 카테고리) 매출액
-- AUTHOR_ID, AUTHOR_NAME, CATEGORY, SALES 출력
-- 저자 ID 오름차순, 카테고리 내림차순
select a.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, SUM(b.PRICE * bs.SALES) as TOTAL_SALES
from BOOK as b
    inner join BOOK_SALES as bs on b.BOOK_ID = bs.BOOK_ID and date_format(bs.SALES_DATE, '%Y-%m') = '2022-01'
    inner join AUTHOR as a on b.AUTHOR_ID = a.AUTHOR_ID
group by a.AUTHOR_NAME, b.CATEGORY
order by a.AUTHOR_ID asc, b.CATEGORY desc;