<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="table">
  <head>
    <title>Students sorted by last name</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
  </head>
  <body>
  <h1>Students sorted by last name</h1>
  <table border="1px double" style="border-color: purple">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Last name</th>
      <th>Phone</th>
      <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="list" type="java.util.List"--%>
    <c:forEach var="student" items="${list}">
      <tr>
        <td>${student.ID}</td>
        <td>${student.name}</td>
        <td>${student.lastName}</td>
        <td>${student.phone}</td>
        <td>${student.email}</td>
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
