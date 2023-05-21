<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="home">
<head>
    <title>Hello World!</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
</head>
<body style="width: auto">
<header class="header-flex"><p>Hello World! </p>
    <p style="font-size: 18px"><a href="${pageContext.request.contextPath}/quick-test">Start quick Test</a></p>
    <div class="dropdown">
        <button class="dropbtn">Authentication </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/login">Log in</a>
            <a href="${pageContext.request.contextPath}/logout">Log out</a>
            <a href="${pageContext.request.contextPath}/registration">Sign up</a>
        </div>
    </div>
</header>
<nav class="nav">
<h1>
    <div class="flex-container">
    <div><a href="${pageContext.request.contextPath}/courses">Courses</a></div>
    <div><a href="${pageContext.request.contextPath}/teachers">Teachers</a></div>
    <div><a href="${pageContext.request.contextPath}/students">Students</a></div>
    <div><a href="${pageContext.request.contextPath}/lectures">Lectures</a></div>
    <div><a href="${pageContext.request.contextPath}/homeworks">Homeworks</a></div>
    <div><a href="${pageContext.request.contextPath}/ad-materials">Additional materials</a></div>
    </div>
</h1>
</nav>
</body>
</html>