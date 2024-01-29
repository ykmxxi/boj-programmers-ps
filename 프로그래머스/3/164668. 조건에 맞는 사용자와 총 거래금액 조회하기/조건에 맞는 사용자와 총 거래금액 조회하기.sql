-- 중고 거래 게시판 정보: USED_GOODS_BOARD
-- 중고 거래 게시판 사용자 정보: USED_GOODS_USER
-- 완료된 중고 거래의 총금액이 70만원 이상인 사람의 회원 ID, 닉네임, 총거래 금액 조회
-- 총거래금액 기준 오름차순 정렬

select u.USER_ID, u.NICKNAME, SUM(b.PRICE) as TOTAL_SALES
from USED_GOODS_USER as u inner join USED_GOODS_BOARD as b on u.USER_ID = b.WRITER_ID
where b.STATUS = 'DONE'
group by u.USER_ID
having sum(b.PRICE) >= 700000
order by TOTAL_SALES asc;