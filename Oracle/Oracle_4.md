## Oracle 4

#### Practice(Work 1, Work 2, Work 3, Work 4)

### 1. Work 1

```sql
-- 문제1) EMPLOYEES Table의 모든 자료를 출력하여라.
SELECT * FROM employees;


-- 문제2) EMPLOYEES Table의 컬럼들을 모두 출력하라.
DESC employees;


-- 문제3) EMPLOYEES Table에서 사원 번호, 이름, 급여, 담당업무를 출력하여라.
SELECT employee_id as "사원 번호", last_name as 이름, salary as 급여,job_id as 담당업무
FROM employees;


-- 문제4) 모든 종업원의 급여를 $300증가 시키기 위해서 덧셈 연산자를 사용하고 결과에 SALARY+300을 디스플레이 합니다.
SELECT last_name, salary,salary+300 as "급여+$300"
FROM employees;


-- 문제5) EMP 테이블에서 사원번호, 이름, 급여, 보너스, 보너스 금액을 출력하여라. 
-- (참고로 보너스는 월급 + (월급*커미션))
SELECT employee_id as 사원번호, last_name as 이름, salary as 급여, salary+NVL(salary*commission_pct, 0) as 보너스
FROM employees;
/*
    NVL(컬럼, 컬럼값이 null이면 설정되는 값)
*/


-- 문제6) EMPLOYEES 테이블에서 LAST_NAME을 이름으로 SALARY을 급여로 출력하여라.
--ALIAS
SELECT last_name as 이름, salary as 급여 
FROM employees;


-- 문제7) EMPLOYEES 테이블에서 LAST_NAME을 Name으로 SALARY * 12를 Annual Salary(연봉)로 출력하여라
SELECT last_name as NAME, salary *12 as "Annual Salary(연봉)"
FROM employees;


-- 문제8) EMPLOYEES 테이블에서 이름과 업무를 연결하여 출력하여라. 
SELECT last_name || '의 업무는 ' || job_id || '입니다.'
FROM employees;


-- 문제9) EMPLOYEES 테이블에서 이름과 업무를 “KING is a PRESIDENT” 형식으로 출력하여라. 
SELECT last_name || ' is a ' || job_id
FROM employees;


-- 문제10) EMPLOYEES 테이블에서 이름과 연봉을 “KING: 1 Year salary = 60000” 형식으로 출력하여라. 
SELECT last_name || ': 1 Year salary = ' || salary*12
From employees;


-- 문제11) EMPLOYEES 테이블에서 JOB을 모두 출력하라.
SELECT DISTINCT(job_id) as 직업종류
FROM EMPLOYEES;

```



### 2. Work 2 

```sql
-- 문제1) EMPLOYEES 테이블에서 급여가 3000이상인 사원의 사원번호, 이름, 담당업무, 급여를 출력하라.
SELECT employee_id, first_name, job_id, salary
FROM employees
WHERE salary >= 3000;


-- 문제2) EMPLOYEES 테이블에서 담당 업무가 ST_MAN인 사원의 사원번호, 성명, 담당업무, 급여, 부서번호를 출력하라.
SELECT employee_id, first_name, job_id,  salary, department_id
FROM employees
WHERE job_id = 'ST_MAN';


-- 문제3) EMPLOYEES 테이블에서 입사일자가 2006년 1월 1일 이후에 입사한 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하라.
SELECT employee_id, first_name, job_id, salary, hire_date, department_id
FROM employees
--WHERE hire_date > '2006/01/01';
WHERE hire_date > TO_DATE('20060101','YYYYMMDD');


-- 문제4) EMPLOYEES 테이블에서 급여가 3000에서 5000사이의 사원의 성명, 담당업무, 급여, 부서번호를 출력하라.
SELECT first_name, job_id, salary, department_id
FROM employees
WHERE salary BETWEEN 3000 and 5000;


-- 문제5) EMPLOYEES 테이블에서 사원번호가 145,152,203인 사원의 사원번호, 성명, 담당업무, 급여, 입사일자를 출력하라
SELECT employee_id, first_name, job_id, salary, hire_date
FROM employees
--WHERE employee_id = 145 OR employee_id =  152 OR employee_id =  204;
WHERE employee_id IN(145, 152, 203);


-- 문제6) EMPLOYEES 테이블에서 입사일자가 05년도에 입사한 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하라.
SELECT employee_id, first_name, job_id, salary, hire_date, department_id
FROM employees
WHERE hire_date LIKE '05%';


-- 문제7) EMPLOYEES 테이블에서 보너스가 없는 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 보너스, 부서번호를 출력하라.
SELECT employee_id, first_name, job_id, salary, hire_date, NVL(commission_pct,0) as Bonus, department_id
FROM employees
WHERE commission_pct  IS NULL;

-- 문제8) EMPLOYEES 테이블에서 급여가 8000이상이고 JOB이 ST_MAN인 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하라
SELECT employee_id, first_name, job_id, salary, hire_date, department_id
FROM employees
WHERE salary>=8000 AND job_id = 'ST_MAN';


-- 문제9) EMPLOYEES 테이블에서 급여가 10000이상이거나 JOB이 ST_MAN인 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하라
SELECT employee_id, first_name, job_id, salary, hire_date, department_id
FROM employees
WHERE salary >= 10000 OR job_id = 'ST_MAN';


-- 문제10) EMPLOYEES 테이블에서 JOB이 ST_MAN, SA_MAN, SA_REP가 아닌 사원의 사원번호, 성명, 담당업무, 급여, 부서번호를 출력하라
SELECT employee_id, first_name, job_id, salary, hire_date, department_id
FROM employees
WHERE  job_id NOT IN ('ST_MAN', 'SA_MAN', 'SA_REP');


-- 문제11) 업무가 AD_PRES이고 급여가 12000이상이거나 업무가 SA_MAN인 사원의 사원번호, 이름, 업무, 급여를 출력하라.
SELECT employee_id, first_name, job_id, salary
FROM employees
WHERE (job_id = 'AD_PRES' AND salary >=12000) OR job_id = 'SA_MAN';


-- 문제12) 업무가 AD_PRES 또는 SA_MAN이고 급여가 12000이상인 사원의 사원번호, 이름, 업무, 급여를 출력하라.
SELECT employee_id, first_name, job_id, salary 
FROM employees
WHERE (job_id = 'AD_PRES' OR job_id = 'SA_MAN') AND salary >=12000;

```



### 3. Work 3 

```sql
-- hr 정렬
-- O문제1) EMPLOYEES 테이블에서 입사일자 순으로 정렬하여 사원번호, 이름, 업무, 급여, 입사일자,부서번호를 출력하라.
SELECT employee_id, first_name, job_id, salary, hire_date, department_id
FROM employees
ORDER BY hire_date ASC;


--O문제2) EMPLOYEES 테이블에서 가장 최근에 입사한 순으로 사원번호, 이름, 업무, 급여, 입사일자,부서번호를 출력하라.
SELECT employee_id, first_name, job_id, hire_date, department_id
FROM employees
Order BY hire_date DESC;


-- O문제3) EMPLOYEES 테이블에서 부서번호로 정렬한 후 부서번호가 같을 경우 급여가 많은 순으로 정렬하여 사원번호, 성명, 업무, 부서번호, 급여를 출력하여라.
SELECT  employee_id, first_name, job_id, department_id, salary
FROM employees
ORDER BY department_id, salary DESC


-- O문제4) EMPLOYEES 테이블에서 첫번째 정렬은 부서번호로 두번째 정렬은 업무로 세번째 정렬은 급여가 많은 순으로 정렬하여 
-- 사원번호, 성명, 입사일자, 부서번호, 업무, 급여를 출력하여라.
SELECT  employee_id, first_name, hire_date, department_id, job_id,salary
FROM employees
ORDER BY department_id, job_id, salary DESC; 


-- hr 표준함수

-- O문제1) EMPLOYEES 테이블에서 King의 정보를 대문자로 검색하고 사원번호, 성명, 담당업무(대문자로),부서번호를 출력하라.
SELECT employee_id, first_name, last_name, UPPER(job_id) , department_id
FROM employees
WHERE UPPER(last_name) LIKE 'KING';


-- O문제2) DEPARTMENTS 테이블에서 (부서번호와 부서이름), 부서이름과 위치번호를 합하여 출력하도록 하라.
SELECT department_id || department_name as "부서번호와 부서이름", department_name || location_id as "부서이름과 위치번호"
FROM departments;
-- CONCAT함수를 쓸 수도 있다. 


-- X문제3) EMPLOYEES 테이블에서 이름의 첫 글자가 ‘K’ 보다 크고 ‘Y’보다 적은 사원의 사원번호, 이름, 업무, 급여, 부서번호를 출력하라. 
-- 단 이름순으로 정렬하여라.
SELECT employee_id, first_name, job_id, department_id
FROM employees
WHERE SUBSTR(first_name,1,1)> 'K' AND  SUBSTR(first_name,1,1) < 'Y'
ORDER BY first_name;


-- O문제4) EMPLOYEES 테이블에서 20번 부서 중  사원번호, 이름, 이름의 문자의 길이, 급여, 급여의 자릿수를 출력하라.
SELECT employee_id, first_name,  length(first_name) as "이름의 길이", salary, length(salary) as "급여의 자릿수"
FROM employees
WHERE department_id = 20;


-- X문제5) EMPLOYEES 테이블의 이름에서  ‘e’자의 위치를 출력하라.
SELECT first_name, INSTR(first_name,'e',1,1)
FROM employees;


-- O문제6) ROUND 함수를 사용하여 4567.678의 소수가 다음과 같이 출력되도록 하라.
--4568
SELECT ROUND(4567.678)
FROM dual;
--4568
SELECT ROUND(4567.678,0)
FROM dual;
--4567.68
SELECT ROUND(4567.678,2)
FROM dual;
--4600
SELECT ROUND(4567.678,-2)
FROM dual;


-- O문제7) EMPLOYEES 테이블에서 부서번호가 80인 사람의 급여를 30으로 나눈 나머지를 구하여 출력하라.
SELECT first_name, MOD(salary,30) 
FROM employees
WHERE department_id = 80;


-- O문제8) EMPLOYEES 테이블에서 30번 부서 중 이름과 담당 업무를 연결하여 출력하여라. 
-- 단 담당 업무를 한 줄 아래로 출력하라. 
SELECT first_name || CHR(10) || job_id as "이름과 담당업무"
FROM employees
WHERE department_id = 30;


-- X문제9) EMPLOYEES 테이블에서 현재까지 근무일 수가 몇주 몇일 인가를 출력하여라. 
-- 단 근무 일수가 많은 사람 순으로 출력하여라.
-- 지나온 날 수 계산하는 방법 3가지 
SELECT  first_name, hire_date, 
TRUNC(SYSDATE - hire_date) as "총 근무 일수"
--TRUNC(SYSDATE - TO_DATE('21/09/07')) as "총 근무 일수",
--TRUNC(TO_DATE('21/09/10') - TO_DATE('21/09/07')) as "총 근무 일수"

FROM employees
ORDER BY "총 근무 일수"


-- O문제10) EMPLOYEES 테이블에서 부서 50에서 급여 앞에 $를 삽입하고 3자리마다 ,를 출력하라
SELECT first_name, TO_CHAR(salary, '$999,999')
FROM employees
WHERE department_id = 50;


-- hr 그룹핑
-- X문제1) EMPLOYEES 테이블에서 모든 SALESMAN(SA_)에 대하여 급여의 평균, 최고액, 최저액, 합계를 구하여 출력하여라.sssssss
SELECT AVG(salary), MAX(salary), MIN(salary), SUM(salary)
FROM employees
--GROUP BY job_id
--HAVING job_id LIKE 'SA_%';
WHERE  job_id LIKE 'SA_%';


-- X문제2) EMPLOYEES 테이블에 등록되어 있는 인원수, 보너스가 NULL이 아닌 인원수, 보너스의 평균, 등록되어 있는 부서의 수를 구하여 출력하라.
SELECT COUNT(*), COUNT(commission_pct), TRUNC(AVG(NVL(salary*commission_pct, 0))), COUNT(DISTINCT(department_id))
FROM employees;
-- COUNT함수는 NULL값을 세지 않는다! 


-- O문제3) EMPLOYEES 테이블에서 부서별로 인원수, 평균 급여, 최저급여, 최고 급여, 급여의 합을 구하여 출력하라.
SELECT department_id, COUNT(department_id), TRUNC(AVG(salary)), MIN(salary), MAX(salary), SUM(salary)
FROM employees
GROUP BY department_id
ORDER BY department_id;


-- O문제4) EMPLOYEES 테이블에서 각 부서별로 인원수,급여의 평균, 최저 급여, 최고 급여, 급여의 합을 구하여 급여의 합이 많은 순으로 출력하여라.
SELECT department_id, COUNT(department_id), AVG(salary), MIN(salary), MAX(salary), SUM(salary)
FROM employees
GROUP BY department_id
ORDER BY SUM(salary) DESC;


-- O문제5) EMPLOYEES 테이블에서 부서별, 업무별 그룹하여 결과를 부서번호, 업무, 인원수, 급여의 평균, 급여의 합을 구하여 출력하여라.
SELECT department_id, job_id, COUNT(first_name),AVG(salary), SUM(salary)
FROM employees
GROUP BY department_id, job_id
ORDER BY department_id;
--같은 그룹끼리 묶고 그 안에서 예를 들어 80번 그룹 안에서 SA_MAN끼리 또 묶은 것으로 이해하면 된다.  


-- ▲문제6) EMPLOYEES 테이블에서 부서 인원이 4명보다 많은 부서의 부서번호, 인원수, 급여의 합을 구하여 출력하여라
SELECT department_id, COUNT(*), SUM(salary)
FROM employees
GROUP BY department_id 
HAVING COUNT(*) > 4;
-- 여기서*은 그룹내 모든 인원!


-- O문제7) EMPLOYEES 테이블에서 최대 급여가 10000이상인 부서에 대해서 부서번호, 평균 급여, 급여의 합을 구하여 출력하여라.
SELECT department_id, ROUND(AVG(salary)), MAX(salary), SUM(salary)
FROM employees
GROUP BY department_id
HAVING MAX(salary) >= 10000;


-- O문제8) EMPLOYEES 테이블에서 업무별 급여의 평균이 10000 이상인 업무에 대해서 업무명,평균 급여, 급여의 합을 구하여 출력하라.
SELECT job_id, AVG(salary), SUM(salary)
FROM employees
GROUP BY job_id
HAVING AVG(salary) >= 10000;


-- X문제9) EMPLOYEES 테이블에서 업무별 전체 월급이 10000을 초과하는 각 업무에 대해서 업무와 월급여 합계를 출력하라. 
-- 단 판매원(SA_)은 제외하고  월 급여 합계로 정렬(내림차순)하라.
SELECT job_id, SUM(salary)
FROM employees 
WHERE job_id NOT LIKE 'SA_%'
GROUP BY job_id 
HAVING SUM(salary) > 10000
ORDER BY SUM( salary) DESC;

```

