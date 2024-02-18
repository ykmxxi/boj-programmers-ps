-- 리뷰를 가장 많이 작성한 회원의 리뷰들을 조회
-- 회원이름, 리뷰 텍스트, 리뷰 날짜
-- 작성일 기준 오름차순, 리뷰 텍스트 기준 오름차순

select m.MEMBER_NAME, r.REVIEW_TEXT, date_format(r.REVIEW_DATE, ('%Y-%m-%d')) as REVIEW_DATE
from REST_REVIEW as r inner join MEMBER_PROFILE as m on r.MEMBER_ID = m.MEMBER_ID
where r.MEMBER_ID = (
    select MEMBER_ID
    from REST_REVIEW
    group by MEMBER_ID
    order by count(MEMBER_ID) desc
    limit 1
)
order by REVIEW_DATE, REVIEW_TEXT;
