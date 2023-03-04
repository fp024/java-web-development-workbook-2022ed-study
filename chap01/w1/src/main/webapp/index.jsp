<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>index</title>
</head>
<body>
<ul>
  <h1>프로젝트 인덱스 페이지</h1>
  <li>
    <h4>IntelliJ 템플릿이 생성한 기본 서블릿</h4>
    <a href="hello-servlet">Hello Servlet</a>
  </li>
  <li>
    <h4>계산기</h4>
    <a href="/calc/input">/calc/input</a>
  </li>
  <li>
    <h4>Todo 목록</h4>
    <a href="/todo/list">/todo/list</a>
  </li>
  <li>
    <h4>Todo 등록</h4>
    <a href="/todo/register">/todo/register</a>
  </li>
  <li>
    <h4>Todo 조회 (tno: 123)</h4>
    <a href="/todo/read?tno=123">/todo/read?tno=123</a>
  </li>
</ul>
</body>
</html>