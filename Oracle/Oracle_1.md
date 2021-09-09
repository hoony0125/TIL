## Oracle 1

#### (주석문, 자료형, 표준함수)

### 1. 주석문 

```
/* 범위 주석문 

table
열(항목): column
행: row, record
*/

-- 한 줄 주석문
```

### 2. 자료형

        Java              	 DB                                                                                      
        String           	VARCHAR2, CHAR(거의 안씀), LONG(2GB까지 가능)                             
        int              	INTEGER(자릿수를 정하지 않음), NUMBER(자릿수를 정해줄 수 있다)
        double          	NUMBER
        Date            	DATE  

**2.0. 테이블 기본**

예시를 보기 전 테이블 생성, 삽입 등 간단하게 기본 쿼리문을 살펴보자

```
-- 테이블 생성 쿼리문
CREATE TABLE 테이블명(
        컬럼명1 자료형,
        컬럼명2 자료형,
                :
);

-- 테이블 컬럼에 값을 삽입하는 쿼리문
INSERT INTO 테이블명(컬럼명1, 컬럼명2 ...)
VALUES( 값1, 값2, ...
```



**2.1. 문자열 **

자바에서 "문자열"   ==  오라클   '문자열' (작은 따옴표 사용)

- CHAR(잘 안쓴다, 그저 참고용)

```
CREATE TABLE TB_CHAR(   -- 테이블을 만들어주었다. 
        COL1 CHAR(10 BYTE),  -- 괄호 안에 크기(기억공간의 크기)를 정해준 것. 
        COL2 CHAR(10 CHAR),
        COL3 CHAR(10)   -- 숫자만 적는 경우, 자동으로 byte로 나온다
);  
-- 테이블을 만들고 나서 좌측 스키마에서 테이블을 누르고 새로고침을 누르면 테이블이 생성된 것을 확인가능

INSERT INTO tb_char( COL1, COL2, COL3)
VALUES('가', '나', '다'); 			  -- DB에서 한글은 3바이트 

INSERT INTO tb_char( COL1, COL2, COL3)
VALUES('가나', '가나', '가나');

INSERT INTO tb_char( COL1, COL2, COL3)
VALUES('가나다', '가나다', '가나다'); 

INSERT INTO tb_char( COL1, COL2, COL3)
VALUES('ABC', 'ABC', 'ABC')			-- DB에서 영문은 1바이트 


SELECT COL1,COL2,COL3, 
        LENGTHB(COL1),LENGTHB(COL2),LENGTHB(COL3)  -- 바이트 출력 
FROM tb_char;

출력값
 								col1  col2  col3의 각각의 바이트
가       	나         	다         10  12  10
가나    	  가나          가나    	10	14	10
가나다 	 가나다       	가나다 	  10  16  10
가나    	  가나          가나    	10	14	10
ABC       	ABC       	 ABC       	10	10	10
								=> 사용한 기억공간(바이트)의 크기

--사용한 기억공간(바이트)의 크기 계산방법
col1, col3
col1 - 10byte로 괄호 안에서 크기를 정했기 때문에 다 10바이트가 출력된다. 
col3 - 그냥 숫자만 넣으면 바이트로 설정된다. 
col2 
가 -> 3byte =3+9(10(char)-1) =>12
가나 -> 6byte =6+8(10(char)-2) =>14 
가나다 -> 9byte = 9+7(10(char)-3) =>16
```

실제 기억공간과 사용한 기억공간을 따져서 결론을 내면, 
 COL1 CHAR(10 BYTE)
 COL3 CHAR(10)  

이 두 경우는 10바이트만큼의 기억공간을 갖고 있기 때문에 한글로는 3자까지만 입력이 가능하고,영어의 경우 1바이트이기 때문에 10자까지 가능하다.

 COL2 CHAR(10 CHAR) 이 경우에는 한글이나 영문이나 상관없이 10자까지 가능하다.



- VARCHAR2

  문자열 중 제일 제일 많이 쓰는 것이 VARCHAR2이다.

  ```
  CREATE TABLE TB_VARCHAR(		// TB_VARCHAR라는 테이블을 만들어준다. 
          COL1 VARCHAR2(10 BYTE),
          COL2 VARCHAR2(10 CHAR),
          COL3 VARCHAR2(10)
  );
  
  INSERT INTO tb_varchar(COL1, COL2, COL3)
  VALUES('ABC', 'ABC', 'ABC');
  
  INSERT INTO tb_varchar(COL1, COL2, COL3)
  VALUES('가나다', '가나다', '가나다');
  
  
  SELECT COL1,COL2,COL3,      -- 바이트 출력 
          LENGTHB(COL1),LENGTHB(COL2),LENGTHB(COL3)
  FROM tb_varchar;
  
  
  출력값 
  					  col1 col2 col3
  ABC	ABC	ABC				3	3	3
  가나다	가나다	가나다		  9   9   9
  
  
  VARCHAR2의 경우, 딱 글자 수대로 기억공간을 사용한다. 
  그리고 최대로 사용가능한 기억공간의 크기는 4000바이트이다. 
  ```



- LONG

LONG은 최대 2GB까지 저장이 가능하고 테이블 당 1개의 컬럼만 사용이 가능하다.

```
CREATE TABLE TB_LONG(
    COL LONG
    -- 1개의 컬럼이 넘어가면 에러가 나기때문에 COL1은 추가할 수 없다. 
);

INSERT INTO tb_long(COL)
VALUES('ABCDE');

SELECT COL --, LENGTHB(COL)  LONG은 용량이 크기 때문에 LENGTHB를 쓰지 않는다
FROM tb_long;
```



**2.2. 숫자** 

- INTEGER

  자릿수를 정하지 않고 사용하지만, 사실 숫자 자료형은 NUMBER를 거의 대부분 쓰기 때문에, 그냥 간단한 용례만 보고 넘어가자 

  ```
  CREATE TABLE TB_INTEGER(
      COL1 INTEGER,
      COL2 INTEGER
  );
  
  INSERT INTO tb_integer(COL1, COL2)
  VALUES(123, 456);
  
  INSERT INTO tb_integer(COL1, COL2)
  VALUES(123, 456.1);
  
  INSERT INTO tb_integer(COL1, COL2)
  VALUES('123', '456');
  ```



- NUMBER

  자릿수를 정할 수 있다. 예시를 통해 보는게 이해가 빠르다!

  ```
  CREATE TABLE TB_NUMBER(
          COL1 NUMBER,	-- 전부 출력
          COL2 NUMBER(5), -- 다섯째 자리 정수 출력
          COL3 NUMBER(5,2), -- 소수점의 자릿수 소수점 포함 숫자 5개 2는 소수점 자릿수
          COL4 NUMBER(*,2) -- 자릿수는 상관없이 소수점 2자릿수까지
  );
  
  INSERT INTO TB_NUMBER(COL1, COL2, COL3, COL4)
  VALUES(1234.5678, 12345.12, 123.456, 123.56789);
  
  출력값 
  col1         col2    col3    col4
  1234.5678	12345	123.46	123.57
  ```

  

**2.3.** 날짜 

- DATE

  날짜 
  연도, 월, 일, 시, 분, 초 모두 출력 가능 

  ```
  CREATE TABLE TB_DATE(
      COL1 DATE,
      COL2 DATE
  );
  
  INSERT INTO TB_DATE(COL1,COL2)
  VALUES('21/09/09', sysdate);
  
  INSERT INTO TB_DATE(COL1,COL2)
  VALUES('21-09-09', sysdate-1);  -- -1을 하면 어제 날짜 출력
  
  INSERT INTO TB_DATE(COL1,COL2)
  VALUES(To_DATE('2021-09-09 11:55:23','YYYY-MM-DD HH:MI:SS'), sysdate); 
  -- 시간까지 같이 나오게 출력 
  ```

  

### 3. 표준함수 

표준함수는 용례 중심으로 개념을 잡고 가도록 하자 

- **DUAL TABLE : 가상 테이블 -> 결과확인 테이블** 

```
SELECT 1 FROM DUAL; 
SELECT 'A' FROM DUAL; 
SELECT '가나다' FROM DUAL; 
SELECT 36*24 FROM DUAL;
```



- **문자함수**

  CHR(NUMBER) -> ASCII값을 문자로 변환한다. 

```
SELECT CHR(65) FROM DUAL;
SELECT CHR(48) FROM DUAL;

출력값
A
0

SELECT '내 점수는 ' || CHR(65) || '입니다' FROM DUAL;
```



- **CONCAT** 

  문자열을 합치는 함수이다.

  ```
  SELECT CONCAT('HELLO','WORLD') FROM DUAL;    
  
  출력값
  HELLOWORlD
  ```



- **LPAD(RPAD)** 

  나머지의 빈칸을 지정문자(또는 빈 문자)로 채운다. 

```
SELECT LPAD('BBB', 10) FROM DUAL;  -- 왼쪽에 10개의 빈칸을 만든다.
SELECT RPAD('BBB', 10) FROM DUAL
SELECT LPAD('BBB', 10, '-') FROM DUAL -- 지정한 문자로 앞의 10칸을 채운다.
SELECT LPAD('BBB', 10, '0') FROM DUAL
```



-  **INSTR**   

   == (자바) indexOf        "abcde"      indexof('b') -> 번지 1 출력

  DB에서는 번지가 1부터 시작한다!

```
SELECT INSTR('123ABC456DEFABC','A') FROM DUAL;
SELECT INSTR('123ABC456DEFABC','A', 6) FROM DUAL;  -- 6번지 이후부터 찾아라 
SELECT INSTR('123ABC456DEFABCABC','A',6, 1) FROM DUAL; -- 6번지 이후 첫번째 
SELECT INSTR('123ABC456DEFABCABC','A',6, 2) FROM DUAL; -- 6번지 이후 두번째
SELECT INSTR('123ABC456DEFABCABC','X') FROM DUAL; -- 못찾고 0번지출력 , DB에서는 1부터 시작!

출력값
4
13
13
16
0
```



- **REPLACE** 

  문자열 치환

```
SELECT REPLACE ('AAAAABCD','A') FROM DUAL;
SELECT REPLACE ('AAAAABCD','A','a') FROM DUAL;

출력값 
BCD
aaaaaBCD
```



- **SUBSTR** 

  (자바) "ABCDE"  ->  substring(1,3)  ==> 1부터 시작해서 3전까지 즉, BC

```
SELECT SUBSTR('ABCDE', 3) FROM DUAL;  -- 3번지부터 끝까지 출력 CDE 
SELECT SUBSTR('ABCDE', 3,1) FROM DUAL; -- 3번지부터 한 문자 출력 C 
```



- **숫자 관련 함수**

- **올림** 

  ```
  SELECT CEIL(13.1) FROM DUAL;
  ```

  

- **내림**

  ```
  SELECT FLOOR(13.9) FROM DUAL;
  ```

  

- **반올림**

  ```
  SELECT ROUND(13.5) FROM DUAL;
  ```

  

- **나눈 나머지 구하기**

```
SELECT MOD(3,2) FROM DUAL;
```



- **승수(제곱 계산)**

```
SELECT POWER(3,2) FROM DUAL;
```



- **부호 -> 출력값 1(양수일 때), 0 , -1(음수일 때)**

```
SELECT SIGN(13.4) FROM DUAL;
SELECT SIGN(0) FROM DUAL;
SELECT SIGN(-0.1) FROM DUAL;

출력값 
1
0
-1
```



- **소수점 버림** 

  ```
  SELECT TRUNC(123.4567) FROM DUAL;
  SELECT TRUNC(123.4567,2 ) FROM DUAL; 
  -- 소수점 두번째 자리까지만 살리고 그 밑으로 버려라 
  SELECT TRUNC(123.4567,1) FROM DUAL;
  SELECT TRUNC(123.4567,-1) FROM DUAL; 
  --십의 자리까지만 살리기 출력값 120
  
  출력값 
  123
  123.45
  123.4
  120
  ```

  

- **변환함수 관련**

- **TO_CHAR = DATE -> VARCHAR2** 

  ```
  SELECT TO_CHAR(SYSDATE) FROM DUAL;  -- 날짜를 문자열로 바꿈 
  SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD-HH-MI-SS' ) FROM DUAL;  
  SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD/ HH:MI:SS' ) FROM DUAL; 
  SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD/ HH:MI:SS' ) FROM TB_DATE; 
  
  SELECT TO_CHAR(100000000, '999,999,999') FROM DUAL;
  ```

  

- **TO_DATE = VARCHAR2 -> DATE**

  ```
  SELECT TO_DATE ('20210909') FROM DUAL;
  SELECT TO_DATE ('20210909', 'YYYYMMDD') FROM DUAL;
  ```



- **TO_NUMBER = VARCHAR2 -> NUMBER** 

  (자바) Integer.parseInt("100");

  ```
  SELECT TO_NUMBER('12345') + 45 FROM DUAL;  -- 문자열을 숫자로 바꾸고 연산 
  SELECT '12345' + 45 FROM DUAL;  -- 연산은 되지만, 문법상 맞는 것이 아니다. 
  ```



- **날짜 관련**

- **LAST_DAY 그 달의 마지막 날** 

  ```
  SELECT LAST_DAY('21/02/09') FROM DUAL;
  
  SELECT LAST_DAY(TO_DATE('211012','YYMMDD')) FROM DUAL; 
  -- 함수를 합쳐서 이렇게 쓸 수도 있다. 
  ```

  

- **년도, 월, 일, 시, 분, 초를 분리해서 각각 구하기**

  ```
  SELECT SYSDATE,
          EXTRACT(YEAR FROM SYSDATE),
          EXTRACT(MONTH FROM SYSDATE),
          EXTRACT(DAY FROM SYSDATE),
          EXTRACT(HOUR FROM CAST(SYSDATE AS TIMESTAMP)), 
   -- SYSDATE 날짜형태를 TIMESTAMP 시간형태로 바꾸고 시간만 추출 
          EXTRACT(MINUTE FROM CAST(SYSDATE AS TIMESTAMP)),
          EXTRACT(SECOND FROM CAST(SYSDATE AS TIMESTAMP))
  FROM DUAL; 
  ```

  