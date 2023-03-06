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



  

## 의견

* ...
  
  

## 정오표

* ...



## 기타

* ...