-- 조회수가 가장 높은 중고거래 게시물의 첨부파일 경로를 조회
-- FILE ID 기준 내림차순, 기본 파일경로: '/home/grep/src/%'

set @path = '/home/grep/src/';
select concat(@path, f.BOARD_ID, '/', f.FILE_ID, f.FILE_NAME, f.FILE_EXT) as FILE_PATH
from USED_GOODS_FILE as f inner join USED_GOODS_BOARD as b using(BOARD_ID)
where b.VIEWS = (
    select max(VIEWS)
    from USED_GOODS_BOARD
)
order by FILE_ID desc;