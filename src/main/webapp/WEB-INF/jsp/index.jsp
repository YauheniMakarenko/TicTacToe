<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Главная</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/form.css">
</head>
<body>
<div>
    <form class="modal">
        <button type="button" onclick="location.href='/allgames'" class="button">Find Game</button>
        <p></p>
        <button type="button" onclick="location.href='/creategame'" class="button">Create Game</button>
    </form>
</div>
</body>
</html>