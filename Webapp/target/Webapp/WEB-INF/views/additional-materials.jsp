<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" style="background-color: #3eb489">
<head>
    <title>Additional materials table</title>
</head>
<body>
<h1>Additional materials table</h1>
<table border="1px" style="font-size: 24px">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Resource type</th>
        <th>Lecture ID</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="list" type="java.util.List"--%>
    <c:forEach var="addMat" items="${list}">
        <tr>
            <td><a href="${pageContext.request.contextPath}/mat-instance?ID=${addMat.ID}">${addMat.ID}</a></td>
            <td>${addMat.name}</td>
            <td>${addMat.resourceType}</td>
            <td>${addMat.lectureID}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav>
    <ul style="font-size: 20px; line-height: 1.5;">
        <li><a href="${pageContext.request.contextPath}">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/add-new-material">Create new additional material</a></li>
        <li><a href="${pageContext.request.contextPath}/number-of-ad-mats-by-resource-type">
            Number of additional materials by resource type</a></li>
    </ul>
</nav>
<footer>
    <p style="color: rebeccapurple">OnlineSchool web-api</p>
</footer>
</body>
</html>