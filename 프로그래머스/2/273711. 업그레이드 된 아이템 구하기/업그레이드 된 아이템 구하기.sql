-- 아이템의 희귀도가 RARE인 아이템들의 다음 업그레이드 아이템 id, 이름, 희귀도

SELECT child.ITEM_ID, child.ITEM_NAME, child.RARITY
FROM ITEM_INFO parent
INNER JOIN ITEM_TREE tree ON parent.ITEM_ID = tree.PARENT_ITEM_ID
INNER JOIN ITEM_INFO child ON tree.ITEM_ID = child.ITEM_ID
WHERE parent.RARITY = 'RARE'
ORDER BY child.ITEM_ID DESC;
