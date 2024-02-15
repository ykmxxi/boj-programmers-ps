-- 몇 시에 입양이 가장 활발하게 일어나는지 알아보려 한다.
-- 0시 부터 23시 까지, 각 시간대별로 입양이 몇 건 발생했는지 조회
-- 결과는 시간대 순으로 정렬

SET @hour = -1;
SELECT (@hour := @hour + 1) AS HOUR
	,(SELECT COUNT(ANIMAL_ID)
      FROM ANIMAL_OUTS
      WHERE HOUR(DATETIME) = @hour) AS COUNT 
FROM ANIMAL_OUTS
WHERE @hour < 23
order by HOUR;