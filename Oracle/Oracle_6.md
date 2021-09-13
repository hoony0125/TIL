## Oracle 6

#### Practice(Work 4, Work 5)

### 1. Work 4 

```sql
--O 1. employees 테이블에서 사원이름 중 a가 포함된 사원이름을 구하고 그 이름 중 앞에서 3자만 추출하여 출력하라.
SELECT SUBSTR(first_name,1,3)
FROM employees
WHERE first_name LIKE '%a%';


--O 2. 이름의 세번째 문자가 a인 모든 사원의 이름을 표시하시오.
SELECT first_name
FROM employees
WHERE SUBSTR(first_name, 3,1) = 'a'


--O 3. 이름이 J,A 또는 M으로 시작하는 모든 사원의 이름(첫 글자는 대문자로, 나머지 글자는 소문자로 표시) 및 이름의 길이를 표시하시오.(열 레이블은 name과 length로 표시)
SELECT INITCAP(first_name) as name, length(first_name) as length
FROM employees
WHERE SUBSTR(first_name,1,1) = 'J' OR SUBSTR(first_name,1,1) = 'A' OR SUBSTR(first_name,1,1) = 'M'
-- WHERE first_name LIKE 'J%' OR  first_name LIKE 'A%' OR  first_name LIKE 'M%'  

--O 4. 이름의 글자수가 6자 이상인 사원의 이름을 소문자로 이름만 출력하시오
SELECT LOWER(first_name)
FROM employees
WHERE length(first_name) >= 6;


--O 5. 이름의 글자수가 6자 이상인 사람의 이름을 앞에서 3자만 구하여 소문자로 출력하시오.
SELECT LOWER(SUBSTR(first_name,1,3))
FROM employees
WHERE length(first_name) >= 6; 


--O 6. 모든 사원의 이름과 급여를 표시하시오. 급여는 15자 길이로 왼쪽에 $기호가 채워진 형식으로 표기하고 열레이블을 월급으로 지정하시오.
SELECT first_name, LPAD(salary,  15, '$') as 월급
FROM employees;
SELECT LENGTHB('$')
FROM DUAL;


--O 7. 사원테이블에서 입사년도별 사원수를 구하라. 
SELECT EXTRACT(YEAR FROM hire_date), COUNT(*)
FROM employees
GROUP BY EXTRACT(YEAR FROM hire_date);



-- 날짜 구하기
SELECT TO_CHAR(sysdate,'MM'),
    TO_CHAR(sysdate,'HH'),
    TO_CHAR(TO_DATE('21/09/11'), 'YYYY')
FROM DUAL;

SELECT TO_CHAR(hire_date, 'YY')
FROM employees 
```



### 2. Work 5

```sql
-- hr
--O 문제1) 사원들의 이름, 부서번호, 부서명을 출력하라
SELECT e.first_name, e.department_id, d.department_id, d. department_name
FROM employees e INNER JOIN departments d 
    ON e.department_id = d.department_id;


SELECT e.first_name, 
    e.department_id, d.department_id, 
    d.department_name 
FROM employees e, departments d
WHERE e.department_id = d.department_id;


--O 문제2) 30번 부서의 사원들의 이름,직업,부서명을 출력하라
SELECT e.first_name, e.job_id, d.department_id,d.department_name
FROM employees e INNER JOIN departments d 
    ON e.department_id = d.department_id
WHERE e.department_id = 30


SELECT e.first_name, e.job_id, d.department_name 
FROM employees e, departments d
WHERE e.department_id = d.department_id
    AND e.department_id = 30;


--O 문제3) 커미션을 받는 사원의 이름, 직업, 부서번호,부서명을 출력하라
SELECT e.first_name, e.job_id, d.department_id, d.department_name
FROM employees e INNER JOIN departments d
    ON e.department_id = d.department_id
WHERE e.commission_pct IS NOT NULL;


SELECT e.first_name, e.job_id, d.department_id, d.department_name, e.commission_pct * e.salary
FROM employees e, departments d
WHERE e.department_id = d.department_id
    AND e.commission_pct IS NOT NULL;


--O 문제4) 지역번호 2500 에서 근무하는 사원의 이름, 직업,부서번호,부서명을 출력하라
SELECT e.first_name, e.job_id, e.department_id, d.department_name,  d.location_id
FROM employees e, departments d
WHERE e.department_id = d.department_id AND d.location_id = 2500;


--O 문제5) 이름에 A가 들어가는 사원들의 이름과 부서이름을 출력하라
SELECT e.first_name, d.department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id 
AND first_name LIKE '%A%';


--▲ 문제6) 사원이름과 그 사원의 관리자 이름을 출력하라  
SELECT a.first_name as 사원, a.manager_id, b.employee_id, b.first_name as 상사
FROM employees a, employees b                -- a: 사원, b:관리자 라는 두개의 테이블이라고 생각하고 할 것!  
WHERE a.manager_id = b.employee_id;   


--O 문제7) 사원이름과 부서명과 월급을 출력하는데 월급이 3000 이상인 사원을 출력하라
SELECT e.first_name, d.department_name, e.salary
FROM employees e, departments d 
WHERE e.department_id = d.department_id AND e.salary >= 3000;


--X 문제8) TJ 이란 사원보다 늦게 입사한 사원의 이름과 입사일을 출력하라   --> cross join에 가깝다, 매우 드물게 사용
SELECT a.first_name, a.hire_date, b.first_name,b.hire_date
FROM employees a, employees b               -- a: TJ, b:사원들 이라고 생각하기! 
WHERE a.first_name = 'TJ' 
        AND a.hire_date < b.hire_date;


--O 문제9) 급여가 3000에서 5000사이인 사원의 이름과 소속부서명 출력하라
SELECT e.first_name, e.salary, d.department_name 
FROM employees e, departments d
WHERE e.department_id = d.department_id AND e.salary > 3000 AND e.salary <5000;


--O 문제10) Accounting 부서 소속 사원의 이름과 입사일 출력하라sssssss
SELECT e.first_name, e.hire_date
FROM employees e, departments d
WHERE e.department_id = d.department_id AND d.department_name = 'Accounting';


--O 문제11) 급여가 3000이하인 사원의 이름과 급여, 근무지
SELECT e.first_name, e.salary, l.city
FROM employees e, departments d, locations l
WHERE e.department_id = d.department_id 
                AND e.salary <= 3000
                AND d.location_id = l.location_id;


/*
--▲ 문제12) 101번 사원에 대해 아래의 결과를 산출하는 쿼리를 작성해 보자. 
--------------------------------------------------------------------------
사번      사원명     job명칭       job시작일자     job종료일자     job수행부서명
---------------------------------------------------------------------------
*/
SELECT a.employee_id, a.first_name, d.job_title, b.start_date, b.end_date, c.department_name 
FROM employees a, job_history b, departments c, jobs d
WHERE a.employee_id = b.employee_id
    AND b.department_id = c.department_id
    AND b.job_id = d.job_id
    AND a.employee_id = 101;

```

