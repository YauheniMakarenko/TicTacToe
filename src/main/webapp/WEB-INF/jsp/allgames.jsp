<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/form.css"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/allgames" method="post" class="modal">
    <div>
        <input type="text" path="name" placeholder="Search..." id="name" autofocus="true" name="search">
        <button type="submit" name="action" value="search">Search</button>
        <br>
        <button type="submit">Search result: ${gameId.gamename}</button>
    </div>
    <br>
    <p>All</p>
    <c:forEach items="${allGames}" var="game">
        <button type="submit">NameGame: ${game.gamename}</button>
    </c:forEach>
</form>
</body>
</html>