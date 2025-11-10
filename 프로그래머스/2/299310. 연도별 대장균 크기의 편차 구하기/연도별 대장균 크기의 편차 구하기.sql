-- 분화된 연도(YEAR), 분화된 연도별 대장균 크기의 편차(YEAR_DEV), 대장균 개체의 ID
-- 분화된 연도별 대장균 크기의 편차 = 분화된 연도별 가장 큰 대장균 크기 - 각 대장균 크기
-- 연도에 대해 오름차순 정렬, 대장균 크기 편차에 대해 오름차순 정렬

SELECT YEAR(ed.DIFFERENTIATION_DATE) as YEAR, m.mc - ed.SIZE_OF_COLONY as YEAR_DEV, ed.ID
FROM ECOLI_DATA AS ed LEFT JOIN(
    SELECT MAX(SIZE_OF_COLONY) mc, YEAR(DIFFERENTIATION_DATE) as y
    FROM ECOLI_DATA
    GROUP BY YEAR(DIFFERENTIATION_DATE)
) AS m ON YEAR(ed.DIFFERENTIATION_DATE) = m.y
ORDER BY YEAR, YEAR_DEV;
