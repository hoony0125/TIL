## Oracle 5

####  Join, SubQuery

### 1. Join

**1.1. 개념**

Join은 두개 이상의 테이블을 묶어 하나의 결과 집합으로 만드는 것이다. 두개 이상의 테이블의 데이터를 검색하는 방법이며, 보통 두개 이상의 행(Row)들의 공통된 값(기본키, 외래키)를 사용해서 Join한다. 

- **기본키(Primary Key)** : 각 행을 구분하는 유일한 열, 기본키 열은 중복되면 안되고, 비어있어서도 안된다는 조건이 있다. 그리고 각 테이블은 기본 키가 하나만 지정되어야 한다. 

- **외래키(Foreign Key)** : 테이블과 다른 테이블의 관계를 맺어주는 키이다. 테이블에서는 외래키이더라도 다른 테이블에서는 PK 또는 UK인 경우가 많다. 

  ```sql
  /*
  Join 
  두개 이상의 테이블의 데이터를 검색하는 방법이다. 
  보통 두개이상의 행(Row)들의 공통된 값(기본키, 외래키)를 사용해 Join한다.
  
  기본키(Primary Key): 테이블에서 중복이 되지 않는 키 == 주민번호 -> PK
  외래키(Foreign Key): 다른 테이블에서 PK, UK인 경우가 많다 -> FK
  
  Join의 종류                                    중요도
  inner join => 교집합                           *****
  full outer join => 합집합 
  cross join
  outer join
      left join                                  ***
      right join                                 ***
  self join                                     *****
  
  */
  ```

  

**1.2. Join의 종류**

![sqljoins_cheatsheet](C:/Users/kkh87/Desktop/TIL/Oracle/Oracle_5.assets/sqljoins_cheatsheet.png)

​    이미지 출처:https://dsin.wordpress.com/2013/03/16/sql-join-cheat-sheet/)



- **INNER JOIN**

  A와 B의 교집합 ,null값이 있는 애들은 아예 출력되지 않는다

  ```sql
  -- ansi SQL방식 -> 여러 DB언어에서 통용되고, 호환성이 좋은 방법 
  SELECT e.employee_id, e.first_name,
      e.department_id, d.department_id,
      d.department_name
  FROM employees e INNER JOIN departments d
      ON e.department_id = d.department_id;
      
      
  --Oracle방식 ->오라클에서 사용되는 방법
  SELECT e.employee_id, e.first_name,
      e.department_id, d.department_id,
      d.department_name
  FROM employees e, departments d 
  WHERE e.department_id = d.department_id;
  
  -- employees테이블이나 jobs 테이블 둘 중 하나에만 있는 키는 아래처럼 e 또는 j를 안 적어도 된다.
  SELECT employee_id, first_name,    
      e.job_id, j.job_id,
      job_title
  FROM employees e, jobs j
  WHERE e.job_id = j.job_id
      AND e.job_id = 'IT_PROG';
  ```

  

- **OUTER JOIN(LEFT, RIGHT, FULL)**

  ```sql
  --left outer join - 벤다이어그램으로 볼 때, A만
  --ansi
  SELECT  e.employee_id, e.first_name,
      e.department_id, d.department_id,
      d.department_name
  FROM employees e LEFT OUTER JOIN departments d 
  -- employees에 있는 애들을 일단 다 받는다. => null값을 갖는 킴벌리도 출력 
      ON e. department_id = d.department_id;
  
  
  --oracle
  SELECT  e.employee_id, e.first_name,
      e.department_id, d.department_id,
      d.department_name
  FROM employees e, departments d 
  WHERE e.department_id = d.department_id(+);   
  -- 플러스가 없는 쪽이 주인공, 플러스가 있는 쪽에서 없는 쪽으로 넘어감 
  
  -- right outer join은 +의 위치만 바꾸면 된다. 
  
  
  --full outer join 
  -- A, B의 합집합, null값이 있어도 모두 출력한다. 
  SELECT  e.employee_id, e.first_name,
      e.department_id, d.department_id,
      d.department_name
  FROM employees e FULL OUTER JOIN departments  d
      ON e.department_id = d.department_id;
  ```

  

- **차집합(A-A∩B / B-A∩B)**

  ```sql
  --차집합 (A-A∩B)
  SELECT  e.employee_id, e.first_name,
      e.department_id, d.department_id,
      d.department_name
  FROM employees e, departments d 
  WHERE e.department_id = d.department_id(+) -- 플러스가 없는 쪽이 주인공, 플러스가 있는 쪽에서 없는 쪽으로 넘어감 
      AND e.department_id IS NULL; -- 킴벌리가 나오게 된다. 
      
      
  --차집합 (B-A∩B)
  SELECT  e.employee_id, e.first_name,
      e.department_id, d.department_id,
      d.department_name
  FROM employees e, departments d 
  WHERE e.department_id(+) = d.department_id
      AND e.department_id IS NULL;  -- 부서는 있는데 직원배치가 안된 데이터들이 나온다. 
  ```

  

- **A∪B - A∩B**

  ```sql
  SELECT  e.employee_id, e.first_name,
      e.department_id, d.department_id,
      d.department_name
  FROM employees e FULL OUTER JOIN departments d 
      ON e.department_id = d.department_id
  WHERE e.department_id IS NULL-- 플러스가 없는 쪽이 주인공, 플러스가 있는 쪽에서 없는 쪽으로 넘어감 
      OR d.department_id IS NULL;
  ```

  

- **세개 테이블 조인**

  ```sql
  -- 부서명, 업무명, Nancy
  SELECT e.first_name, 
      e.department_id, d.department_id,
      e.job_id, j.job_id,
      d.department_name, j.job_title
  FROM employees e, departments d, jobs j 
  WHERE e.first_name = 'Nancy' 
      AND e. department_id = d.department_id
          AND e.job_id = j.job_id;
  ```

  

- **Self Join**

  동일한 테이블을 조인한다. 그러나 다른 테이블이라고 생각하기! 

  ```sql
  SELECT a.employee_id, a.first_name,
      a.manager_id, b.employee_id,
      b.first_name
  FROM employees a, employees b -- A : 사원, B : 상사로 생각하기
  WHERE a. manager_id = b.employee_id;
  ```



- **CROSS JOIN**

  존재만 할 뿐 정말 거의 안 쓰기 때문에, 몰라도 된다고 한다.

  ```sql
  -- ansi 
  SELECT  e.employee_id, e.first_name,
      e.department_id, d.department_id,
      d.department_name
  FROM employees e CROSS JOIN departments d; 
  
  -- oracle
  SELECT e.employee_id, e.first_name,
      e.department_id, d.department_id,
      d.department_name
  FROM employees e, departments d; 
  ```




### 2. SubQuery

SubQuery는 Query안의 Query이다. 

SubQuery는 SELECT, FROM, WHERE 안에 들어갈 수 있다. 



- **형태**

SELECT     단일 ROW 단일 COLUMN (산출되는 데이터는 한개, 컬럼도 한개)
FROM        다중 ROW 다중 COLUMN 가능
WHERE      다중 ROW 다중 COLUMN 가능



**2.1. SELECT**

```sql
SELECT employee_id, first_name,
        (SELECT first_name, salary  
         -- 단일 행, 단일 컬럼만 가능 first_name이 있는데, 추가로 salary가 올 수 없다.  
       	 FROM employees
       	 WHERE employee_id = 100)    
FROM employees;



SELECT first_name, SUM(salary), AVG(salary) 
-- 이렇게 그룹함수와 함께 실행시키고 싶다면, 서브쿼리 사용으로 가능 
FROM employees;



SELECT employee_id,  -- 경고가 뜨긴 하지만, 되기는 된다. 다른 방법은 다음에 설명
    (SELECT SUM(salary) FROM employees),
    (SELECT TRUNC(AVG(salary)) FROM employees)
FROM employees;
```



**2.2. FROM**

FROM 안의 서브쿼리 처리가 먼저 일어난다. 

```sql
SELECT first_name, salary
FROM (SELECT employee_id, first_name, salary        --> *로 대체 가능 
      FROM employees
      WHERE department_id =100)
WHERE salary > 8000;



-- 업무별로 급여의 합계, 인원수, 사원명, 월급을 출력하고 했을 때,   
-- 아래처럼 GROUP BY로 묶고 나면, 사원명, 월급 출력에 있어서 한계가 있다. 
SELECT SUM(salary), COUNT(*)
FROM employees
GROUP BY job_id;



-- 바로 위의 한계를 해결하기 위해 서브쿼리로 하나의 테이블을 만들고, 그 테이블과 조인해서 코딩! 
SELECT e.employee_id, e.salary,
            e.job_id, j.job_id,
            j."급여의 합계", j.인원수
FROM employees e, (SELECT job_id, SUM(salary) as "급여의 합계", COUNT(*) as "인원수"
                   FROM employees
                   GROUP BY job_id)  j
WHERE e.job_id = j.job_id;
```



**2.3. WHERE**

```sql
-- 평균 급여보다 많이 받는 사원
SELECT first_name, salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);



-- department_id = 90인 사원의 업무(job_id)만을 산출
SELECT job_id, first_name, department_id
FROM employees
WHERE job_id IN(SELECT job_id
                FROM employees
                WHERE department_id = 90);



-- 부서별로 가장 급여를 적게 받는 사원들과 같은 급여를 받는 사원 ==> 그닥 유의미한 정보는 아니다. 
-- 부서별로 다 다르게 적게 받는 사람들과 같아봤자 의미는 없지.. 그러므로 아래의 코딩을 하는 것이다!
SELECT first_name, salary, department_id
FROM employees
WHERE salary IN (SELECT MIN(salary)     -- 조건 한개를 매치
                 FROM employees
                 GROUP BY department_id);
                                
                      
                      
-- 부서별로 가장 급여를 많이 받는 사원의 이름, 급여, 부서번호
SELECT department_id, salary, first_name
FROM employees
WHERE (department_id, salary) IN (SELECT department_id, MAX(salary)  -- 조건 두개를 매치 
                                   FROM employees
                                   GROUP BY department_id)
ORDER BY department_id;
```

