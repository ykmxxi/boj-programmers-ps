-- 경제 카테고리에 속하는 도서들의 id, 저자명, 출판일을 출력
-- 출판일 기준 오름차순

select b.BOOK_ID, a.AUTHOR_NAME, date_format(b.PUBLISHED_DATE, '%Y-%m-%d')
from BOOK as b inner join AUTHOR as a on b.AUTHOR_ID = a.AUTHOR_ID
where b.CATEGORY = '경제'
order by PUBLISHED_DATE;
