<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html style="background-color: #3eb489">
  <head>
    <title>Homeworks table</title>
  </head>
  <body>
  <h1>Homeworks table</h1>
  <table border="1px"; style="font-size: 24px">
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
  <nav>
    <ul style="font-size: 20px; line-height: 1.5;">
      <li><a href="${pageContext.request.contextPath}">Home</a></li>
    </ul>
  </nav>
  <footer>
    <p style="color: rebeccapurple">OnlineSchool web-api</p>
  </footer>
  </body>
</html>
