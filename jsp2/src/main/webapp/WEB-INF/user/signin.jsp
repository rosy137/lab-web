<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Post</title>
	</head>
	<body>
		<header>
            <h1>로그인</h1>
        </header>
        <nav>
        <ul>
            <li><c:url var="mainPage" value="/"></c:url> 
                <a href="${ mainPage }">메인 페이지</a>
            </li>
        </ul>
    </nav>
        <main>
            <form method="post">
                <div>
                    <input type="text" name="username" placeholder="사용자 아이디 입력" required autofocus/>
                </div>
                <div>
                    <input type="password" name="password" placeholder="사용자 비밀번호 입력" required/>
                </div>
                <div>
                    <input type="submit" value="로그인"/>
                </div>
            </form>
        </main>
	</body>
</html>