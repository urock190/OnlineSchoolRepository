<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="table">
  <head>
    <title>Homeworks table</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
  </head>
  <body>
  <h1>Homeworks table</h1>
  <table border="1px double" style="border-color: purple">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Task</th>
      <th>Number of tasks</th>
      <th>Deadline</th>
      <th>Lecture ID</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="list" type="java.util.List"--%>
    <c:forEach var="homework" items="${list}">
      <tr>
        <td>${homework.ID}</td>
        <td>${homework.name}</td>
        <td>${homework.task}</td>
        <td>${homework.numberOfTasks}</td>
        <td>${homework.deadline}</td>
        <td>${homework.lectureID}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <nav class="nav">
    <ul>
      <li><a href="${pageContext.request.contextPath}/">Home</a></li>
    </ul>
  </nav>
  <footer>
    <p>OnlineSchool web-api</p>
  </footer>
  </body>
</html>
