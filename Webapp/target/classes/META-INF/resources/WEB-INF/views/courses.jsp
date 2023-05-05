<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="table">
  <head>
    <title>Courses table</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
  </head>
  <body>
  <h1>Courses table</h1>
  <table border="1px double" style="border-color: purple">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="coursesList" type="java.util.List"--%>
    <c:forEach var="course" items="${coursesList}">
      <tr>
        <td>${course.ID}</td>
        <td>${course.name}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <nav class="nav">
    <ul>
      <li><a href="${pageContext.request.contextPath}/">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/add-new-course">Create new course</a></li>
    </ul>
  </nav>
  <footer>
    <p>OnlineSchool web-api</p>
  </footer>
  </body>
</html>
