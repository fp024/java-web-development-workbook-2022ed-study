# 4장 스프링과 스플이 Web MVC

> 드디어 스프링 MVC ✨✨✨
>
> * 프로젝트: [springex](springex)



## 4.1 의존성 주입과 스프링

* ...

### 스프링의 시작

* ...

#### 의존성 주입

* ...

#### 프로젝트 생성

* ...

* 서블릿 테스트 헬퍼 클래스들은 제거했다.

  

#### 실습_01 의존성 주입하기

* ...
* 하단에 스프링 메뉴 안보이면... Shift 2번 눌러서 Spring 메뉴 열어주자..

* **스프링의 빈 설정 테스트**

* 테스트 환경설정 어노테이션은 새로 나온 것이 있어서 아래와 같이 쓰면 되겠다.

  ```java
  @SpringJUnitConfig(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
  ```

* spring-test의 로거 레벨이 달라졌나보다..  지면과 약간 로그 출력 수준이 다름.





### ApplicationContext와 빈(Bean)

* ...



#### 실습_02 SampleDAO 주입하기

* ...

  

#### `<context:component-scan>`

* ...

#### 실습_03 `@Service`, `@Repository`

* ...



### 인터페이스를 이용한 느슨한 결합

* ...

#### 실습_04 SampleDAO를 인터페이스로 변경하기

* ...

#### `@Qualifier` 와 `@RequiredArgsConstructor`를 함깨 사용해야하는 상황시 문제

아래 코드가

```java
@Service
@ToString
@RequiredArgsConstructor
public class SampleService {
  @Qualifier("normal") private final SampleDAO sampleDAO;
}
```

아래 처럼 되길 기대하는데, 

```java
@Service
@ToString
public class SampleService {
  private final SampleDAO sampleDAO;

  public SampleService(@Qualifier("normal") SampleDAO sampleDAO) {
    this.sampleDAO=sampleDAO;
  }
```

아무 설정이 없으면 생성자의 매개변수 앞에 `@Qualifier("normal")`  를 안붙혀줌.

lombok.config에 아래 내용을 추가해서 복사해주도록 하야함.

```
lombok.copyableAnnotations += org.springframework.beans.factory.annotation.Qualifier
```



* 스프링의 빈(Bean)으로 지정되는 객체들
  * ...
* XML이나 어노테이션으로 처리하는 객체
  * ...



### 웹 프로젝트를 위한 스프링 준비

* ...



#### 실습_05 DataSource 구성하기

* ...
* root-context.xml에 HikariCP 설정하기
  * ...





## 4.2 MyBatis와 스프링 연동

* ...

### MyBatis 소개

* ...

#### MyBatis와 스프링의 연동 방식

* MyBatis로 단독으로 개발하고 스프링에서 DAO를 작성해서 처리하는 방식
* MyBatis와 스프링을 연동하고 Mapper 인터페이스만 이용하는 방식



* MyBatis를 위한 라이브러리 들
  * 스프링 관련: spring-jdbc, spring-tx
  * MyBatis관련: mybatis, mybatis-spring



* MyBatis를 위한 스프링의 설정 - SessionFactory
  * ...



#### 실습_01 Mapper 인터페이스 활용하기

* ...
* IntelliJ의 경고를 피하기 위해 Mapper 인터페이스의 `@Autowired` required 속성을 false로 설정하는 내용이 있어서... 요즘 해결되었나 찾아봤는데... 왠지 해결할 우선순위가 그다지 높은 것 같지 않음. . 😓
  * https://youtrack.jetbrains.com/issue/IDEA-210302/Spring-Mybatis-Unsupported-tag-mybatis-springscan-for-namespace-http-mybatis.org-schema-mybatis-spring
  * `@MapperScan` 붙인 설정클래스 일부러 만들어서 해봣을 때도 안됨 😓 왠지 안해주기로 한듯 😂



#### 실습_02 XML로 SQL 분리하기

* ...

  





## 의견

* ...
  
  

## 정오표

* ...



## 기타

* ...