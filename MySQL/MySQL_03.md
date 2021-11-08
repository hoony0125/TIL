## MySQL

#### 테이블 생성 

```sql
CREATE TABLE Ocn_User
(
    `USERID`    VARCHAR(45)    NOT NULL    COMMENT '사용자 아이디', 
    `PWD`       VARCHAR(60)    NOT NULL    COMMENT '비밀번호', 
    `EMAIL`     VARCHAR(50)    NOT NULL    COMMENT '이메일', 
    `ADDRESS`   VARCHAR(60)    NOT NULL    COMMENT '주소', 
    `NICKNAME`  VARCHAR(20)    NOT NULL    COMMENT '닉네임', 
    `DEL`       INT(1)         NULL        COMMENT '삭제유무', 
    `AUTH`      INT(1)         NULL        COMMENT 'AUTH', 
     PRIMARY KEY (USERID)
);
```

#### 컬럼명 변경

```sql
ALTER TABLE ocn_user CHANGE address ADDRESS1 VARCHAR(50);
ADDRESS가 기존의 컬럼명, ADDRESS1이 새로운 컬럼명
```

#### 컬럼 추가

```SQL
ALTER TABLE ocn_user ADD ADDRESS3 VARCHAR(100) NULL;
마지막 NULL은 NOT NULL도 가능
```

#### 컬럼 위치 수정 

```sql
ALTER TABLE ocn_user MODIFY COLUMN NICKNAME VARCHAR(20) AFTER PWD;
PWD컬럼 뒤로 NICKNAME컬럼을 이동
```

#### 컬럼을 첫번째 위치로 이동

```sql
ALTER TABLE 테이블명 MODIFY COLUMN 컬럼명 자료형 FIRST;
```

