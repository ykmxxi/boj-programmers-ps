-- 희귀도가 LEGEND 아이템들의 가격의 총합
select sum(PRICE) as TOTAL_PRICE
from ITEM_INFO
where RARITY = 'LEGEND';
