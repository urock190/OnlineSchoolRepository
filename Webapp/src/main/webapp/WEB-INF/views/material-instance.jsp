<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Additional material instance</title>
</head>
<body>
<h1>Additional material</h1>
<%--@elvariable id="material" type="src.main.java.models.AdditionalMaterial"--%>
    <div style="font-size: 24px">
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
</body>
</html>