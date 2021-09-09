## Oracle 2

#### (SELECT 1, SELECT 2)

### 1. SELECT 1

형식:
            SELECT (컬럼(항목)명, 함수, SUB QUERY)
            FROM (테이블명, SUB QUERY);

SELECT도 역시 용례 위주로 설명이 이어지기 때문에 먼저 해당 설명에서 사용하는 EMPLOYEES테이블의 컬럼들을 살펴보자 

EMPLOYEE_ID    	 						NUMBER(6) 
FIRST_NAME            				    VARCHAR2(20) 
LAST_NAME     							VARCHAR2(25) 
EMAIL       	  							  VARCHAR2(25) 
PHONE_NUMBER        	 		  VARCHAR2(20) 
HIRE_DATE     	 									 DATE 
JOB_ID       	 							   VARCHAR2(10) 
SALARY              						    NUMBER(8,2)
COMMISSION_PCT      		 	    NUMBER(2,2)
MANAGER_ID           					    NUMBER(6)
DEPARTMENT_ID       	   			   NUMBER(4)    



- **EMPLOYEES테이블에서 모든 데이터를 가져와** 

```
SELECT * FROM employees; 
```



- **현재 계정(HR)에서 어떤 테이블들이 있는지, 모든 테이블들을 보여준다.**

```
SELECT * FROM TAB; 
```



- **EMPLOYEES Table의 컬럼들을 모두 출력**

  ```
  DESC employees;
  ```

  

- **추출하면서 연산을 통해 연봉계산**

```
SELECT employee_id, first_name,salary*12  
FROM employees;
```



- **ALIAS : as를 붙이거나 안붙여도 되는데, 명칭을 내가 새로 입력하는 것**

```
SELECT employee_id AS 사원번호, salary as 월급
FROM employees;
-- 사원번호로 뜨게끔 하고, id를 받아올 수 있다, AS나 as 나 상관없다. 만약 월 급이 되면 (띄어쓰기) 에러가 발생한다
```

```
SELECT employee_id AS 사원번호, salary as "월 급", salary *12 "일년치 급여(연봉)"
FROM employees  
-- 이렇게 월급이 띄어쓰기로 되어 있을 때는 큰 따옴표를 이용해서 적어야 한다. 
-- as를 적지 않아도 적용된다. 띄어쓰기가 있으므로 큰 따옴표 사용 
```

```
SELECT first_name || '의 월급은' || salary || '입니다.' as "이름 + 월급"
FROM employees;
-- xxx의 월급은 xxx입니다. "이름 + 월급"
```



- **distinct : 중복행을 삭제** 

```
SELECT DISTINCT department_id
FROM employees;
-- 어떤 값들이 들어가 있는지 중복없이 보여준다.
```



- **NVL(컬럼, 컬럼값이 null이면 설정되는 값)**

```
SELECT last_name as 이름, salary as 급여, NVL(salary*commission_pct, 0) as 보너스
FROM employees;
-- null값이 뜨면 0을 넣는다. 
```



### 2. SELECT 2 

형식: 

SELECT
FROM 
WHERE   ==   조건절 -> if 



비교연산자 (>, <, <=, >=, =, !=, <>)   
NULL, IS NULL, IS NOT NULL 
         

논리연산자 

AND(&&), OR(| |) 
우선비교는 소괄호로 ( )



- **이름 -> Julia인 사람만 뽑기**

```
SELECT first_name, last_name, salary 
FROM employees
WHERE first_name = 'Julia';
```



- **월급 -> $9000 이상인 사원만 뽑기**

```
SELECT first_name, salary
FROM employees
WHERE salary >= 9000;
```



- **이름 -> Shanta 보다 큰 이름 -> 철자가 더 뒤에 오는 사람, 출석부번호가 더 뒤에 오는 이름**

```
SELECT first_name
FROM employees
WHERE first_name >= 'Shanta';
```



- **이름 첫 문자가 J보다 크면서 Shanta보다 뒤에 오는 이름 검색하기** 

```
SELECT first_name
FROM employees
WHERE first_name > 'J' AND first_name >= 'Shanta'; 
-- 이렇게 하면 검색하는 속도에 차이가 난다. 
```



- **매니저가 없는 사원(Manager id가 null인 사람) 구하기**

```
SELECT first_name, manager_id
FROM employees
WHERE manager_id IS NULL;

-- manager_id = NULL 이렇게는 안된다(자바 방식). IS NULL로 하기! 
```

```
SELECT first_name, manager_id
FROM employees
WHERE manager_id IS NOT NULL; 
-- null인 한명 빼고 다 출력 (null인 경우가 사장 한명 뿐이다)
```



- **AND(자바에서 &&)**

이름이 John이고 월급이 5000이상

```
SELECT first_name, salary
FROM employees
WHERE first_name = 'John' AND salary >= 5000;
```



- **OR (자바에서 | |)**

  이름이 John이거나 Vollman인 사람 

```
SELECT first_name, last_name
FROM employees
WHERE first_name = 'John' OR last_name = 'Vollman';
```



- **2007년 12월 31일 이후에 입사한 사원을 출력 두 방법**

```
SELECT first_name, hire_date
FROM employees
WHERE hire_date > '07/12/31';
			또는
WHERE hire_date > TO_DATE('20071231','YYYYMMDD');
```



- **예약어**

ALL(=AND), ANY(=OR)
IN, NOT IN  -  범위를 잡는게 아니라, 특정 값이 있는지 
BETWEEN AND - 범위연산
LIKE 



- **ALL**

```
SELECT first_name, last_name
FROM employees
WHERE first_name = ALL('Julia', 'John');  
-- = first name이 줄리아 이면서 존인 사람 => 출력값 없음 
=> 이럴 때 ALL을 안씀, 사용빈도 적다
```



- **ANY**

```
SELECT first_name, last_name
FROM employees
WHERE first_name = ANY('Julia', 'John'); -- first name이 줄리아 또는 존인 사람 


SELECT first_name, salary
FROM employees
WHERE salary = ANY(8000, 3200, 6000);  
```



- **IN**

```
SELECT first_name, salary
FROM employees
WHERE salary IN(8000, 3200, 6000);  
- IN에서는 =을 쓰지 않음 
-- 8000, 3200, 6000인 사람 출력 


SELECT first_name, salary
FROM employees
WHERE salary = 8000 OR salary = 3200 OR salary = 6000;


SELECT first_name, salary
FROM employees
WHERE salary NOT IN(8000, 3200, 6000);
-- 세 월급을 받는 사람 제외 모두 출력 
```



- **BETWEEN AND**

```
SELECT first_name, salary
FROM employees
WHERE salary >= 3200 AND salary <= 9000;


SELECT first_name, salary
FROM employees
WHERE salary BETWEEN 3200 AND 9000;
-- 3200이상 9000이하 


SELECT first_name, salary
FROM employees
WHERE salary NOT BETWEEN 3200 AND 9000;
-- 3200미만 또는 9000초과 
```



- **LIKE**

```
SELECT first_name
FROM employees
WHERE first_name LIKE 'G_ra_d';
-- 언더바_가 한 글자를 의미하고, 그 한 글자가 어떤 글자가 와도 다른 글자가 같으면 출력 


SELECT first_name
FROM employees
WHERE first_name LIKE 'K%y';
-- %는 중간에 몇글자가 들어가든지 상관없이 양 옆이 같으면 출력 


SELECT first_name, hire_date
FROM employees
WHERE hire_date LIKE '06%';
-- 맨 앞이 06이면 뒤는 상관없이 출력


SELECT first_name, hire_date
FROM employees
WHERE hire_date LIKE '%07';
-- 맨뒤가 07이면 뒤는 상관없이 출력


SELECT first_name
FROM employees
WHERE first_name LIKE '%h%';
-- 위치에 상관없이(맨앞이든 맨뒤든) h가 포함되어 있으면 출력 
```

