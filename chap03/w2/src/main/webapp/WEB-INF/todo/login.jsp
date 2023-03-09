<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>로그인 폼</title>
</head>
<body>
<c:if test="${param.result == 'error'}">
  <h1>로그인 에러</h1>
</c:if>

  <form method="post" action="/login">
    <input type="text" name="mid">
    <input type="password" name="mpw">
    <button type="submit">LOGIN</button>
  </form>
</body>
</html>
