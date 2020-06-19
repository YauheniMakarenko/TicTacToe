<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Spring Boot WebSocket Chat Application | CalliCoder</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/form.css">
</head>
<body>
<noscript>
    <h2>Sorry! Your browser doesn't support Javascript</h2>
</noscript>
<div id="username-page">
    <div class="username-page-container">
        <h1 class="title">Type your username</h1>
        <form id="usernameForm" name="usernameForm">
            <div class="form-group">
                <input type="text" id="name" placeholder="Username" autocomplete="off" class="form-control"/>
            </div>
            <div class="form-group">
                <button type="submit" class="accent username-submit">Start Chatting</button>
            </div>
        </form>
    </div>
</div>

<div id="chat-page" class="hidden">
    <form id="messageForm" name="messageForm" nameForm="messageForm" class="modal">
        <table align="center" cellspacing="50">
            <tbody>
            <tr>
                <td><input class="cell" id="00" x="0" y="0" name="00" value="" type="submit" onclick="give(this.id)">
                </td>
                <td><input class="cell" id="01" x="0" y="1" name="01" value="" type="submit" onclick="give(this.id)">
                </td>
                <td><input class="cell" id="02" x="0" y="2" name="02" value="" type="submit" onclick="give(this.id)">
                </td>
            </tr>
            <tr>
                <td><input class="cell" id="10" x="1" y="0" name="10" value="" type="submit" onclick="give(this.id)">
                </td>
                <td><input class="cell" id="11" x="1" y="1" name="11" value="" type="submit" onclick="give(this.id)">
                </td>
                <td><input class="cell" id="12" x="1" y="2" name="12" value="" type="submit" onclick="give(this.id)">
                </td>
            </tr>
            <tr>
                <td><input class="cell" id="20" x="2" y="0" name="20" value="" type="submit" onclick="give(this.id)">
                </td>
                <td><input class="cell" id="21" x="2" y="1" name="21" value="" type="submit" onclick="give(this.id)">
                </td>
                <td><input class="cell" id="22" x="2" y="2" name="22" value="" type="submit" onclick="give(this.id)">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>