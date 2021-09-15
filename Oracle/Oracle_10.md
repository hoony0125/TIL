## Oracle 10 

#### 무결성 제약조건, 뷰, 시퀀스

### 1. 무결성 제약 조건(Integrity Constraint Rule)

- 무결성(Integrity) : 데이터베이스 자료의 오류 없는 정확성과 안정성을 의미 

무결성 제약 조건 : 무결성을 유지하기 위해 컬럼에 지정하는 규칙

Primary Key : 기본키, NULL을 허용하지 않는다. 중복을 허용하지 않는다. 
                        ex) id, 주민번호
Unique Key : 고유키, NULL은 허용한다. 그러나 중복을 허용하지 않는다. 
                        ex) e-mail
Foreign Key : 외래키, 테이블과 테이블을 연결하는 것이 목적이다. Join이 목적
                        NULL을 허용한다. 
                        외래키로 설정된 컬럼은 연결된 테이블의 컬럼에서 PK나  UK로 설정되어 있다. 
CHECK : 범위를 설정, 값을 지정할 수도 있다. 지정된 값 외에는 사용할 수 없다. NULL을 허용한다. 

NOT NULL : NULL을 허용하지 않는다. 



- **NOT NULL**

  ```sql
  CREATE TABLE TB_TEST04(
      COL1 VARCHAR2(10) NOT NULL,
      COL2 VARCHAR2(20)
  );
  
  INSERT INTO tb_test04(COL1,COL2)
  VALUES('AAA','111');
  
  INSERT INTO tb_test04(COL1,COL2)
  VALUES('AAA','');
  
  INSERT INTO tb_test04(COL1,COL2)  -- COL1은 NULL값을 허용하지 않는다. 
  VALUES('','222');
  
  INSERT INTO tb_test04(COL1)
  VALUES('CCC');
  
  INSERT INTO tb_test04(COL2)   -- COL1은 NULL값을 허용하지 않는다.
  VALUES('333');
  ```

  

- **CHECK**

  지정된 값들 중에서만 삽입하거나 NULL값만 가능

  ```sql
  CREATE TABLE TB_CHECK(
      COL1 VARCHAR2(10),
      COL2 VARCHAR2(20),
      CONSTRAINT CHK_01 CHECK(COL1 IN('사과', '배', '바나나')),
      CONSTRAINT CHK_02 CHECK(COL2 > 0 AND COL2 <=10)
  );
  
  INSERT INTO tb_check(COL1, COL2)
  VALUES('사과', 5);
  
  INSERT INTO tb_check(COL1, COL2)
  VALUES('귤', 5);             -- 제약조건1 위배 
  
  INSERT INTO tb_check(COL2)
  VALUES(5);                     -- CHECK는 NULL값 허용
   
   INSERT INTO tb_check(COL1, COL2)
  VALUES('사과', 0);        -- 제약조건2 위배 
  ```

  

- **Primary Key** 

  기본키, Primary Key = Unique + NOT NULL

  ```sql
  CREATE TABLE TB_TEST05(
      COL1_PK VARCHAR2(10) CONSTRAINT PK_TEST_01 PRIMARY KEY,
      COL2 VARCHAR2(20)
  );
  
  INSERT INTO tb_test05(COL1_PK, COL2)      -- 한번 더 실행하면 중복 때문에 에러가 발생한다.
  VALUES('AAA', '111');
  
  INSERT INTO tb_test05(COL1_PK, COL2)
  VALUES('BBB', '');
  
  INSERT INTO tb_test05(COL1_PK)
  VALUES('CCC');
  
  INSERT INTO tb_test05(COL1_PK, COL2)            -- NULL값으로 인한 에러가 발생한다. 
  VALUES('', '111');
  
  
  -- 이렇게는 PK 두개를 만들 수는 없다. 
  CREATE TABLE TB_TEST06(
      COL1_PK VARCHAR2(10) CONSTRAINT PK_TEST_02 PRIMARY KEY,
      COL2_PK VARCHAR2(10) CONSTRAINT PK_TEST_03 PRIMARY KEY, 
      -- table can have only one primary key
      COL2 VARCHAR2(20)
  );
  
  
  -- 한 테이블에 PK를 두개 갖게 하는 방법 - 그닥 권장되지는 않지만, 종종 필요한 경우가 있다고 한다. 
  CREATE TABLE TB_TEST06(
      COL1 VARCHAR2(10),
      COL2 VARCHAR2(10), 
      COL3 VARCHAR2(20),
      CONSTRAINT PK_TEST_02 PRIMARY KEY(COL1, COL2)
  );
  
  
  -- PK를 만드는 다른 방법 (일단 만들고, ALTER에서 ADD)
  CREATE TABLE TB_TEST07(
      COL1 VARCHAR2(10),
      COL2 VARCHAR2(10)
  );
  
  ALTER TABLE TB_TEST07
  ADD 
  PRIMARY KEY(COL1);
  
  
  -- 기본키를 삭제하는 방법 
  ALTER TABLE TB_TEST07
  DROP
  PRIMARY KEY;
  ```

  

- **Unique Key**

  고유키, 중복된 값 사용불가, NULL은 허용 

  ```sql
  CREATE TABLE TB_TEST08(
  COL1 VARCHAR2(10) CONSTRAINT UK_TEST_01 UNIQUE
  COL2 VARCHAR(20)
  );
  
  
  INSERT INTO TB_TEST08(COL1,COL2)
  VALUES('AAA', '111');
  
  INSERT INTO TB_TEST08(COL1,COL2)
  VALUES('','222');
  
  ```

  

- **Foreign Key**

  외래키, Join이 목적, 테이블 연결, 기본테이블에서 컬럼이 PK 또는 UK로 되어 있어야 한다. 
  NULL을 허용한다 

  ```sql
  CREATE TABLE DEPT(
  DEPARTMENT_ID VARCHAR2(10),
  DEPARTMENT_NAME VARCHAR2(20),
  LOCATION_ID NUMBER,
  CONSTRAINT PK_DEPT_TEST PRIMARY KEY(DEPARTMENT_ID)
  );
  
  
  INSERT INTO DEPT(DEPARTMENT_ID, DEPARTMENT_NAME, LOCATION_ID)
  VALUES('10', '기획', 100);
  
  INSERT INTO DEPT(DEPARTMENT_ID, DEPARTMENT_NAME, LOCATION_ID)
  VALUES('20', '관리', 110);
  
  INSERT INTO DEPT(DEPARTMENT_ID, DEPARTMENT_NAME, LOCATION_ID)
  VALUES('30', '개발', 120);
  
  
  -- 연결해줄 테이블 생성 
  CREATE TABLE EMP(
  EMPNO VARCHAR2(10),
  EMPNAME VARCHAR2(20),
  DEPARTMENT_ID_FK VARCHAR2(10),   
  -- 외래키 생성 시에 주의할 점은 PK로 잡을 때 가급적 바이트 크기를 맞출 것 (맞춰놓는게 혹시 모를 에러를 방지할 수 있다)
  CONSTRAINT FK_EMP_TEST FOREIGN KEY(DEPARTMENT_ID_FK) 
  -- 설명을 위해 _FK를 붙였을 뿐 대부분의 경우 PK와 이름(DEPARTMENT_ID)을 같게 한다. 
  REFERENCES DEPT(DEPARTMENT_ID)	-- 연결해줄 대상 
  );
  
  -- 외래키에 들어갈 값은 DEPT테이블의 기본키에 들어가 있는 값들 중에서만 가능하다!
  INSERT INTO EMP(EMPNO,EMPNAME, DEPARTMENT_ID_FK)
  VALUES(1, '홍길동', '30');
  
  INSERT INTO EMP(EMPNO,EMPNAME, DEPARTMENT_ID_FK)
  VALUES(2, '성춘향', '10');
  
  INSERT INTO EMP(EMPNO,EMPNAME, DEPARTMENT_ID_FK)
  VALUES(3, '일지매', '20');
  
  ```

  

### 2. 뷰(View)

- View : 가상 테이블 
             실체가 없는 테이블
             다른 테이블에 접근하기 위한 테이블 
             

테이블 <----- View ----- User

뷰는 테이블을 보는 창문이다. 

한개의 뷰로 여러 개의 테이블을 검색할 수 있다.
속도가 빠르다
제한설정을 할 수 있다. -----> readonly  

뷰를 생성한 후에는 그 뷰를 그냥 테이블처럼 생각해도 무방하다. 뷰는 기본적으로 readonly로 많이 사용되기는 하지만, 테이블의 데이터를 수정하는 것도 가능하긴 하다. 다만, 그닥 바람직하지는 않다.  



- **예시**

  ```sql
  -- 뷰의 생성 
  CREATE VIEW UB_TEST01
  AS 
  SELECT job_id, job_title, min_salary
  FROM Jobs;
  
  
  SELECT *
  FROM UB_TEST01;         --> 뷰를 통해 jobs 테이블을 보는 것이다. 
  
  
  -- 뷰의 수정 
  INSERT INTO ub_test01(JOB_ID, JOB_TITLE, min_salary)
  VALUES('DEVELOPER', '웹 개발자', 10000);                                    
  -- 뷰를 통해 JOBS 테이블에 삽입한 것이다. 
  
  
  
  -- READ ONLY
  CREATE VIEW DEPTVIEW
  AS
  SELECT department_id, department_name, location_id
  FROM departments
  WITH READ ONLY;     -- INSERT, DELETE, UPDATE가 불가능하다
  
  
  SELECT *
  FROM DEPTVIEW;  -- VIEW를 통해서 DEPARTMENTS테이블을 보는 것이다. 
  
  
  
  -- 뷰의 활용 
  -- 사원번호, 이름, 부서번호, 부서명, 지역번호 
  CREATE VIEW DEPT_EMP_VIEW
  AS
  SELECT e.employee_id, e.first_name, d. department_id, d.department_name, d.location_id
  FROM employees e, departments d
  WHERE e.department_id = d.department_id;
  
  
  SELECT *
  FROM DEPT_EMP_VIEW				-- 뷰를 한번 만들어두면, 
  WHERE employee_id =100;			-- 원하는 데이터를 이렇게 간편하게 불러올 수 있다.
  
  ```

  

  ### 3. 시퀀스 

- SEQUENCE : 유일한 값(중복되지 않은 값)을 생성해주는 오브젝트 
                         예를 들면, 회원번호, 게시글 번호 등 
                         초기화가 안 된다. -> 삭제 후 다시 생성해야 한다. 



```sql
-- SEQUENCE
CREATE SEQUENCE TESTSEQ 
INCREMENT BY 1          	 	 -- 1씩 증가 
START WITH 10               	 -- 시작 숫자
-- 여기 아래부터는 옵션
MAXVALUE 100
MINVALUE 1;


-- CURRVAL = 현재의 SEQ
SELECT TESTSEQ.CURRVAL 
FROM DUAL;


-- NEXTVAL = 진행값 (NEXTVAL로 시작을 해주어야 CURRVAL이 작동한다.)
SELECT TESTSEQ.NEXTVAL 
FROM DUAL;                        --  실행하면 계속 진행된다. 


-- 수정 
ALTER SEQUENCE TESTSEQ
INCREMENT BY 3;                   -- 3씩 증가하도록 수정 


-- 삭제
DROP SEQUENCE TESTSEQ;


-- 활용
INSERT INTO employees(employee_id, last_name, email, hire_date, job_id)
VALUES(EMPLOYEES_SEQ.nextval, '홍', 'hgd@naver.com', '21/09/13', 'IT_PROG');

SELECT *
FROM employees;
ROLLBACK;              			 -- SEQUENCE로 삽입한 부분을 안뜨게 한다. 
```

