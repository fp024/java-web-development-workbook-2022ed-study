# 3장 세션/쿠키/필터/리스너

> 이번 장도 중요한 내용이다.✨ 서블릿의 기본 👍
>
> * 프로젝트: [w2](w2)



## 3.1 세션과 필터

* ...

### 무상태에서 과거를 기억하는 법

* ...

#### 쿠키를 생성하는 방법

* ...
* 서버에서 자동으로 생성되는 쿠키
* 개발자가 생성하는 쿠기



### 서블릿 컨텍스트와 세션 저장소

* ...

#### 세션을 통한 상태 유지 메커니즘

* ...

* HttpServletRequest의 getSession() 메서드를 호출하면 JSESSIONID 이름의 쿠키가 요청에 있었는지 확인하고 없으면 새로운 값을 만들어 세션 저장소에 보관

  > MockHttpServletRequest에서는 그냥 무조건 만들긴 했음..😉
  >
  > * https://jakarta.ee/specifications/platform/9/apidocs/
  > * https://javaee.github.io/javaee-spec/javadocs/
  >   * 10 버전은 클래스 검색시 문서가 링크가 깨진다.. 9 또는 8보는게 나을듯..



#### 프로젝트의 생성과 복사

* ...



#### HttpServletRequest의 getSession()

* ...
* 요청에 JSESSIONID 정보 유무에 다라 다음과 같이 동작
  * 있으면... : 
    * 세션 저장소에 새로운 ID로 공간을 만들고 해당 공간에 접근할 수 있는 객체를 반환
    * 새로운 ID는 부라우저에 JSESSION의 값으로 전송 (세션 쿠키)
  * 없으면... : 
    * 세션 저장소에서 JSESSIONID 값을 이용해서 할당된 공간을 찾고 이 공간에 접근할 수 있는 객체를 반환



### 세션을 이용하는 로그인 체크

* ...



#### 실습_01 등록할 때, 로그인 체크 하기

* ...
* 쿠키를 일부러 제거하고 등록(register) 서블릿에 접근하면 응답시 JSESSIONID 쿠키를 생성해준다.
  ![image-20230309123121527](doc-resources/image-20230309123121527.png)



#### 실습_02 로그인 처리 컨트롤러 작성하기

* ...



### 필터를 이용한 로그인 체크

* ...
* Filter에서 init(), destroy()가 default라서 필수 구현이 아님. 



#### 실습_03 로그인 체크 구현

* ...

* sendRedirect() 이후 바로 return으로 끝내지 않고 계속 진행해버리면.. (아래에서 `return;`을 빼먹으면...)

  ```java
     if (session.getAttribute("loginInfo") == null) {
        resp.sendRedirect("/login");
        return; // 끝내는것이 중요. 다음 필터로 넘기면 안 됨.
      }
  ```

  다음과 같은 예외가 발생함.

  ```
  java.lang.IllegalStateException: 응답이 이미 커밋된 후에는 forward할 수 없습니다.
  	at org.apache.catalina.core.ApplicationDispatcher.doForward(ApplicationDispatcher.java:285) ~[catalina.jar:10.1.7]
  	at org.apache.catalina.core.ApplicationDispatcher.forward(ApplicationDispatcher.java:277) ~[catalina.jar:10.1.7]
  	at org.fp024.w2.controller.TodoListController.doGet(TodoListController.java:31) [classes/:?]
  ```

  sendRedirect로 응답 주소가 정해졌는데.. TodoListController 까지 들어가서 forward코드를 만날때 예외가 나는 것 같다.

  ```java
        request.getRequestDispatcher(TODO_VIEW_ROOT.concat("/list.jsp")) //
            .forward(request, response); // 예외 발생
  ```

  









## 의견

* ...
  
  

## 정오표

* ...



## 기타

* ...