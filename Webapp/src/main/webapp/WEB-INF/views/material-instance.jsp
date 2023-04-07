<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="table">
<head>
    <title>Additional material instance</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<h1>Additional material</h1>
<%--@elvariable id="material" type="src.main.java.models.AdditionalMaterial"--%>
    <div>
    <p>ID: ${material.ID}</p>
        <c:choose>
        <c:when test="${material.resourceType eq 'URL'}">
            <p style="color: red">Name: <a href="${material.name}">${material.name}</a></p>
        </c:when>
        <c:otherwise>
            <p>Name: ${material.name}</p>
        </c:otherwise>
        </c:choose>
    <p>Resource type: ${material.resourceType}</p>
    <p>Lecture ID: ${material.lectureID}</p>
    </div>
<nav class="nav">
    <ul>
        <li><a href="${pageContext.request.contextPath}">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/ad-materials">Back to Additional materials table</a></li>
    </ul>
</nav>
</body>
</html>