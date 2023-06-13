<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
<!-- JSP는 서버 재시작이 필요없지만 서블릿은 필요함 -->
    <h1>Index</h1>
    <ul>
        <li>
            <a href = "ex1">첫번째 Servlet</a>
        </li>
        <li>
            <a href = "ex2">두번째 Servlet</a>
        </li> 
        <li>
            <a href = "ex3">포워드</a>
        </li> 
        <li>
            <a href = "ex4">리다이렉트</a>
        </li>
        <li>
            <!-- URL 상대 경로: http://localhost:8081/contextRoot/intro.jsp -->
            <a href = "intro.jsp">JSP 소개</a>
        </li>
        <li>
            <a href = "form.jsp">form 제출</a>
        </li>
        <li>
            <a href = "main.jsp">include 지시문(directive)</a>
        </li>
        <li>
            <a href = "scriptlet.jsp">스크립트릿(scriptlet)</a>
        </li>
        <li>
            <a href = "actiontag.jsp">액션 태그(action tag)</a>
        </li>
        <li>
            <a href = "el.jsp">EL(Expression Language)</a>
        </li>
        <li>
            <a href = "jstl.jsp">JSTL</a>
        </li>
        <li>
            <a href = "form2.jsp">form 제출</a>
        </li>
        <li>
            <a href = "form2-result.jsp?username=admin&color=b">클릭1</a>
        </li>
        <li>
            <c:url var="reqURL" value="form2-result.jsp">
                <c:param name="username" value="adm&in"></c:param>
                <c:param name="color" value="g"></c:param>
            </c:url>
            <a href = "${ reqURL }">클릭2</a>
        </li>
        <li>
            <a href = "format.jsp">포맷팅</a>
        </li>
        <li>
            <a href = "mvc">MVC pattern</a>
        </li>
    </ul>
</body>
</html>
