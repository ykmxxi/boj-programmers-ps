-- 잡은 물고기 중 가장 큰 물고기의 길이를 cm를 붙여 출력

SELECT CONCAT(MAX(LENGTH),'cm') as MAX_LENGTH
FROM FISH_INFO
