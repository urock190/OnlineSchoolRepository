<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="table">
  <head>
    <title>Lectures until 2024 sorted by lecture date</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
  </head>
  <body>
  <h1>Lectures until 2024 sorted by lecture date</h1>
  <table border="1px double" style="border-color: purple">
    <thead>
    <tr>
      <th> Name </th>
      <th> Materials number </th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="map" type="java.util.Map"--%>
    <c:forEach var="entrySet" items="${map}">
      <tr>
        <td>${entrySet.key}</td>
        <td>${entrySet.value}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <nav class="nav">
    <ul>
      <li><a href="${pageContext.request.contextPath}">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/lectures">Back to Lectures table</a></li>
    </ul>
  </nav>
  <footer>
    <p>OnlineSchool web-api</p>
  </footer>
  </body>
</html>
