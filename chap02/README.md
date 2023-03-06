# 2장 웹과 데이터 베이스

> 도커로 MariaDB를 사용하고 있으니 그걸 사용하도록 하자~🎈
>
> * 프로젝트: [jdbcex](jdbcex)



## 2.1 JDBC 프로그래밍 준비

* ...

### MariaDB의 설치화 생성

* ...

#### MariaDB 설치

* ...

* Docker로 MariaDB 설치 

  ```sh
  docker run \
    --name mariadb_10.x \
    -v {호스트의 저장경로}:/var/lib/mysql \
    -e TZ="Asia/Seoul" \
    -e MARIADB_ROOT_PASSWORD='암호' \
    -p 13306:3306 \
    -d mariadb:10.9.3 \
    --character-set-server=utf8mb4 \
    --collation-server=utf8mb4_unicode_ci
  ```

  * 사용중인 버전은: `10.9.3`
  * 외부 포트: `13306`

  

#### 데이터베이스 생성과 사용자 계정 추가

* root 계정으로 접속해서 아래 내용 실행

  ```sql
  CREATE DATABASE webdb CHARACTER SET UTF8MB4;
  
  CREATE USER 'webuser'@'localhost' IDENTIFIED BY 'webuser';
  CREATE USER 'webuser'@'%' IDENTIFIED BY 'webuser';
  
  GRANT ALL PRIVILEGES ON webdb.* TO 'webuser'@'localhost';
  GRANT ALL PRIVILEGES ON webdb.* TO 'webuser'@'%';
  ```



### 프로젝트 생성과 MariaDB 준비

#### IntelliJ의  MariaDB 설정

![image-20230307030454675](doc-resources/image-20230307030454675.png)

* ...



#### 프로젝트내 MariaDB 설정

* build.gradle에 mariadb-java-client 디펜던시 추가

  ```groovy
  implementation "org.mariadb.jdbc:mariadb-java-client:${mariadbJavaClientVersion}"
  ```



#### JDBC 프로그램 작성 순서

* ...
  

### 실습_01 테스트 프로그램 작성하기

* ...



#### 실습_02 데이터베이스 테이블 생성

* TIMESTAMP 한계가 2037년까지면 얼마 안남은것 같다..?😅 새로 만들 때는 DATETIME 사용하는 것이 나을 것 같다.

* 물론 시간값이 해당 속성에 대해 전혀 무의미하면 DATE 타입이 좋음.👍

  ```sql
  CREATE TABLE tbl_todo (
      tno         INT AUTO_INCREMENT PRIMARY KEY,
      title       VARCHAR(100) NOT NULL,
      dueDate     DATE NOT NULL,
      finished    TINYINT DEFAULT 0
  );
  ```

  

#### 실습_03 데이터 insert

```sql
INSERT INTO tbl_todo (title, dueDate, finished)
VALUES ('Test...', '2022-12-31', 1);
```



#### 실습_04 데이터 select

```sql
SELECT * FROM tbl_todo WHERE tno < 10;
```



#### 실습05 데이터 업데이트

```sql
UPDATE tbl_todo
   SET finished = 0,
       title = 'Not yet...'
 WHERE tno = 3;
```



#### 실습_06 데이터 DELETE

```sql
DELETE 
  FROM tbl_todo
 WHERE tno > 5;
```



### DML과 쿼리(select)의 차이

* DML은 몇개의 데이터가 처리되었는지 숫자로 결과 반환
* SELECT 문은 데이터를 반환



#### JDBC 프로그래밍을 위한 API와 용어들

* java.sql.Connection
* java.sql.Statement / PreparedStatement
* java.sql.ResultSet
* Connection Pool 과 DataSource
* DAO (Data Access Object)
* VO (Value Object) 혹은 엔티티(Entity)





  

## 의견

* ...
  
  

## 정오표

* ...



## 기타

* ...