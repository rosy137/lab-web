<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>POST</title>
</head>
<body>
    <header>
        <h1>포스트 목록 페이지</h1>
    </header>
    <nav>
        <ul>
            <li><c:url var="mainPage" value="/"></c:url> <a
                href="${ mainPage }">메인 페이지</a></li>
            <li><c:url var="postList" value="/post"></c:url> <a
                href="${ postList }">포스트 목록</a></li>
            <li><c:url var="postModify" value="/post/modify">
                    <c:param name="id" value="${ post.id }"></c:param>
                </c:url> <a href="${ postModify }">포스트 수정</a></li>
            <li><c:url var="postCreate" value="/post/create"></c:url>
                <a href="${ postCreate }">새 포스트 작성</a></li>
        </ul>
    </nav>
    <main>
        <table>
            <thead>
                <tr>
                    <th>NO.</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>createdTime</th>
                    <th>modifiedTime</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ posts }" var="post">
                    <tr>
                        <td>${ post.id }</td>
                        <td>
                            <c:url var="postDetail" value="/post/detail">
                                <c:param name = "id" value = "${ post.id }"></c:param>
                            </c:url>
                            <a href = "${ postDetail }">${ post.title }</a>
                        </td>
                        <td>${ post.author }</td>
                        <td>${ post.createdTime }</td>
                        <td>${ post.modifiedTime }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
</body>
</html>