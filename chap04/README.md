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







## 의견

* ...
  
  

## 정오표

* ...



## 기타

* ...