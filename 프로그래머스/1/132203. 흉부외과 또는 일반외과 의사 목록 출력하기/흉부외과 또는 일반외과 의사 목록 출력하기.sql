-- 코드를 입력하세요
-- 흉부외과(CS) or 일반외과(GS)
-- 고용일자 내림차순, 이름 오름차순

SELECT DR_NAME, DR_ID, MCDP_CD, date_format(HIRE_YMD, '%Y-%m-%d') as HIRE_YMD
FROM DOCTOR
WHERE MCDP_CD = 'CS' OR MCDP_CD = 'GS'
ORDER BY HIRE_YMD DESC, DR_NAME ASC;
