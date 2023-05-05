<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="table">
  <head>
    <title>Students By Courses Number And Sorted By Last Name</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
  </head>
  <body>
  <h1>Students grouped by courses number and sorted by last name</h1>
  <table border="1px double" style="border-color: purple">
    <thead>
    <tr>
      <th> Last name </th>
      <th> Name </th>
      <th> Courses number </th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="map" type="java.util.Map"--%>
    <c:forEach var="entrySet" items="${map}">
      <tr>
        <td>${entrySet.key.lastName}</td>
        <td>${entrySet.key.name}</td>
        <td>${entrySet.value}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <nav class="nav">
    <ul>
      <li><a href="${pageContext.request.contextPath}/">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/students">Back to Students table</a></li>
    </ul>
  </nav>
  <footer>
    <p>OnlineSchool web-api</p>
  </footer>
  </body>
</html>
