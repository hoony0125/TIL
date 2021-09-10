## Oracle 3

#### (ORDER BY, GROUP BY, HAVING)

### 1. ORDER BY

ORDER BY == Sorting

- **형식** 

SELECT [COLUMN VALUE SUBQUERY]
FROM [TABLE SUBQUERY] 
ORDER BY [COLUMN (ASC, DESC)]



- **ASC/DESC 아무것도 안 적으면 디폴트는 ASC**

```sql
SELECT first_name, hire_date
FROM employees
ORDER BY hire_date;


SELECT first_name, hire_date
FROM employees
ORDER BY hire_date ASC;


SELECT first_name, salary
FROM employees
ORDER BY salary DESC;
```



- **ALIAS를 사용해서도 ORDER BY가 가능하다.**

```sql
SELECT employee_id, first_name, salary *12 as "ANNSAL"
FROM employees
ORDER BY ANNSAL DESC;
```



- **ASC(오름차순)를 줬을 때, NULL값이 있으면 NULL은 제일 아래에서 출력된다.**
  **NULLS FIRST를 적어주면, NULL값이 위에서 나온다.** 

```sql
SELECT first_name, NVL(commission_pct,0)
FROM employees
ORDER BY commission_pct ASC NULLS FIRST;
```



- **DESC(내림차순)를 줬을 때, NULL값이 있으면, NULL은 제일 위에서 출력된다.**
  **NULLS LAST를 주면, NULL값이 밑에서 나온다.**

```sql
SELECT first_name, NVL(commission_pct,0)
FROM employees
ORDER BY commission_pct DESC NULLS LAST;
```



### 2. GROUP BY, HAVING

GROUP BY : 그룹으로 묶는 기능(묶기 전에 사용)

HAVING : 묶었을 때 조건 (묶고난 후)

- **GROUP BY 기능을 좀 더 잘 이해하기 위해 DISTINCT를 이용한 방법을 살펴보자** 

```sql
SELECT DISTINCT  job_id, first_name
FROM employees;
-- DISTINCT로 중복을 없애도 first_name을 추가하면, 중복 상관없이 다 나온다.(first_name에 맞춰)


SELECT job_id, first_name
FROM employees
GROUP BY job_id;
-- job_id로 묶게 되면 SELECT로는 묶은 것만 볼 수 있다. 
-- first_name도 같이 쓰면 에러가 발생한다. (DISTINCT와 다르다)
```



- **통계 - GROUP FUNCTION**

  COUNT - 개수 세기
  SUM - 합계
  AVG - 평균
  MAX - 최대
  MIN - 최소



- **WHERE(조건절)를 이용할 수 있지만, 한계가 있다.** 

  ```sql
  SELECT first_name, job_id
  FROM employees
  WHERE job_id = 'IT_PROG';
  
  
  SELECT COUNT(salary), COUNT(*), SUM(salary), AVG(salary), MAX(salary), MIN(salary) min
  FROM employees
  WHERE job_id = 'IT_PROG';
  -- 여기서 SELECT에 job_id를 추가로 넣으면 에러가 뜨는데, 이는 그룹핑한 것이 아니라, 조건에 맞는 데이터들을 단순 통계를 낸 것이라 에러가 난다.
  -- 단일 그룹의 그룹 함수가 아니다
  ```

  

- **GROUP BY, ORDER BY 사용 예시** 

  ```sql
  SELECT job_id, COUNT(*), SUM(salary), AVG(salary), MAX(salary), MIN(salary)
  FROM employees
  GROUP BY job_id
  ORDER BY SUM(salary) DESC; 
  ```



- **HAVING절은 group by 없이 단독으로는 못쓴다.**

```sql
SELECT department_id, SUM(salary)
FROM employees
GROUP BY department_id 
HAVING SUM(salary) >= 50000;
```



- **실습 예시** 

```sql
 SELECT job_id, SUM(salary)     	    --4
 FROM employees
 WHERE salary >= 5000                	--1
 GROUP BY job_id                        --2
 HAVING SUM(salary)>20000         		--3
 ORDER BY SUM(salary) DESC;    			--5
```



