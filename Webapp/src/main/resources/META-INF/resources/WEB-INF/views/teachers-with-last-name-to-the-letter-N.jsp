<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="table">
  <head>
    <title>TeachersWithLastNameToTheLetterN</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
  </head>
  <body>
  <h1>Teachers with last name to the letter N</h1>
  <table border="1px double" style="border-color: purple">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Last name</th>
      <th>Phone</th>
      <th>Email</th>
      <th>Course ID</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="list" type="java.util.List"--%>
    <c:forEach var="teacher" items="${list}">
      <tr>
        <td>${teacher.ID}</td>
        <td>${teacher.name}</td>
        <td>${teacher.lastName}</td>
        <td>${teacher.phone}</td>
        <td>${teacher.email}</td>
        <td>${teacher.courseID}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <nav class="nav">
    <ul>
      <li><a href="${pageContext.request.contextPath}/">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/teachers">Back to Teachers table</a></li>
    </ul>
  </nav>
  <footer>
    <p>OnlineSchool web-api</p>
  </footer>
  </body>
</html>
