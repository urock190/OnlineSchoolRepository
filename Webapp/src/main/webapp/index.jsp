<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html style="background-color: #b68bf5">
<head>
    <title>Hello World!</title>
</head>
<body>
<p>Hello World!</p>
<h1 style="text-align: center; line-height: 2.5;">
    <a href="${pageContext.request.contextPath}/courses">Courses</a><br>
    <a href="${pageContext.request.contextPath}/teachers">Teachers</a><br>
    <a href="${pageContext.request.contextPath}/students">Students</a><br>
    <a href="${pageContext.request.contextPath}/lectures">Lectures</a><br>
    <a href="${pageContext.request.contextPath}/homeworks">Homeworks</a><br>
    <a href="${pageContext.request.contextPath}/ad-materials">Additional materials</a><br>
</h1>
</body>
</html>