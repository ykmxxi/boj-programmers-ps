-- 2022년 3월의 오프라인/온라인 상품 판매 데이터
-- 날짜, 상품 id, 유저 id, 판매량
-- offline의 경우 userid null
-- 판매일 오름차순, 상품 id 오름차순, 유저 id 오름차순

SELECT date_format(SALES_DATE, '%Y-%m-%d') as SALES_DATE, PRODUCT_ID, NULL as USER_ID, SALES_AMOUNT
FROM OFFLINE_SALE
WHERE SALES_DATE between '2022-03-01' and '2022-03-31'
UNION ALL
SELECT date_format(SALES_DATE, '%Y-%m-%d') as SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
FROM ONLINE_SALE
WHERE SALES_DATE between '2022-03-01' and '2022-03-31'
ORDER BY SALES_DATE, PRODUCT_ID, USER_ID;