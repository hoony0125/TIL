
CREATE TABLE MEMBER(
	ID VARCHAR2(50) PRIMARY KEY,
	PWD VARCHAR2(50) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	EMAIL VARCHAR2(50) UNIQUE,
	AUTH NUMBER(1) NOT NULL
);

SELECT * FROM MEMBER;

-- id 확인 방법 2가지 
--id를 조건문으로 줘서 데이터에 abc가 있는지 확인하는 방법 
SELECT ID
FROM MEMBER
WHERE ID='abc';

--아이디 카운트를해서 1이 나오면 있는 것, 0이 나오면 없는 것 => pk로 넣어뒀기 때문에 0 아니면 1만 나온다. 
SELECT COUNT(*)
FROM MEMBER
WHERE ID='abc';


-- 로그인 
SELECT ID
FROM MEMBER
WHERE ID='abc' AND PWD='123'