## Oracle 9

#### Practice(Work 6)

###  1. Work 6

```sql
-- 문제1) EMPLOYEES 테이블에서 (Kochhar(last_name)의 급여)보다 많은 사원의 정보를 사원번호,이름,담당업무,급여를 출력하라.
SELECT b.employee_id, b.last_name, b.department_id, b.salary
FROM employees a, employees b
WHERE a.last_name = 'Kochhar'
    AND a.salary < b.salary;
        
-- 서브쿼리 사용        
SELECT employee_id, last_name, job_id, salary
FROM employees
WHERE salary >= (SELECT salary
                 FROM employees
                 WHERE last_name = 'Kochhar') 
  AND last_name != 'Kochhar';    -- Kochhar가 아닌 경우라는 조건을 추가해봄 



-- 문제2) EMPLOYEES 테이블에서 (급여의 평균)보다 적은 사원의 사원번호,이름,담당업무,급여,부서번호를 출력하여라.  
SELECT employee_id, first_name, job_id, salary, department_id 
FROM employees
WHERE salary < (SELECT AVG(salary) 
                FROM employees);



-- X 문제3) EMPLOYEES 테이블에서 (100번 부서의 최소 급여)보다 최소 급여가 많은 다른 모든 부서를 출력하라
SELECT department_id, MIN(salary)
FROM employees
GROUP BY department_id
HAVING MIN(salary) > (SELECT MIN(salary)
                      FROM employees 
                      WHERE department_id = 100);


-- 문제4) (업무별로 최소 급여)를 받는 사원의 정보를 사원번호,이름,업무,부서번호를 출력하여라. 
-- 단 업무별로 정렬하여라.
SELECT employee_id, first_name, job_id, department_id,salary
FROM employees
WHERE (job_id, salary) IN (SELECT job_id, MIN(salary)
                           FROM employees
                           GROUP BY job_id)
ORDER BY job_id;


-- 문제5) EMPLOYEES 과 DEPARTMENTS 테이블에서 업무가 SA_MAN 사원의 정보를 이름,업무,부서명,근무지를 출력하라.
SELECT e.first_name, e.job_id, d.department_name, d.location_id
FROM employees e, departments d 
WHERE e.department_id = d.department_id 
   AND e.job_id = 'SA_MAN'; 

-- 서브쿼리와 조인 사용도 가능
SELECT e.first_name, e.job_id, d.department_name, location_id
FROM( SELECT first_name, job_id, department_id
      FROM employees
      WHERE job_id = 'SA_MAN') e, departments d 
WHERE e.department_id = d.department_id;


-- 문제6) EMPLOYEES 테이블에서 (가장 많은 사원)을 갖는 MANAGER의 사원번호를 출력하라.
SELECT manager_id, count(*)
FROM employees
GROUP BY manager_id
HAVING COUNT(manager_id) = (SELECT  MAX(count(manager_id))
                            FROM employees
                            GROUP BY manager_id);


-- 문제7) EMPLOYEES 테이블에서 (가장 많은 사원이 속해 있는 부서 번호)와 사원수를  출력하라.
SELECT department_id, COUNT(*)
FROM employees
GROUP BY department_id
HAVING COUNT(department_id) = (SELECT MAX(COUNT(*))
                               FROM employees
                               GROUP BY department_id);


-- 문제8) EMPLOYEES 테이블에서 (사원번호가 123인 사원의 직업)과 같고 (사원번호가 192인 사원의 급여)보다 많은 사원의 사원번호,이름,직업,급여를 출력하라.
SELECT a.employee_id, a.first_name, a.salary
FROM employees a, employees b, employees c
WHERE b.employee_id = 123 AND a.job_id = b.job_id AND c.employee_id = 192 AND a.salary > c.salary;

-- 서브쿼리 사용하는 방법
SELECT employee_id, first_name, salary
FROM employees
WHERE job_id = (SELECT job_id FROM employees WHERE employee_id = 123)
                AND salary > (SELECT salary 
                              FROM employees 
                              WHERE employee_id = 192);
        
        
-- 문제9)(직업별로 최소급여)를 받는 사원의 정보를 사원번호,이름,업무,부서명을 출력하라.
-- 조건1 :직업별로 내림차순정렬
SELECT e.employee_id, e.first_name, e.job_id, d.department_name
FROM employees e, departments d
WHERE (job_id, salary) IN (SELECT job_id, MIN(salary)
                           FROM employees
                           GROUP BY job_id)
   AND e.department_id = d.department_id
ORDER BY job_id DESC;


-- 문제10) EMPLOYEES 테이블에서 (50번 부서의 최소 급여)를 받는 사원보다 많은 급여를 받는 사원의 정보를 사원번호,이름,업무,입사일자,급여,부서번호를 출력하라. 
-- 단 50번은 제외
SELECT employee_id, first_name, job_id, hire_date, salary, department_id
FROM employees
WHERE salary > (SELECT MIN(salary)
                FROM employees
                WHERE department_id = 50)
AND department_id <> 50 ; 


-- 문제11) EMPLOYEES 테이블에서 (50번 부서의 최고 급여)를 받는 사원보다 많은 급여를 받는 사원의 정보를 사원번호,이름,업무,입사일자,급여,부서번호를 출력하라. 
-- 단50번은 제외
SELECT employee_id, first_name, job_id, hire_date, salary, department_id
FROM employees
WHERE salary > (SELECT MAX(salary)
                FROM employees
                WHERE department_id = 50)
AND department_id <> 50 ; 


```



