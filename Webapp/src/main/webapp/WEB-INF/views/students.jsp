<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html style="background-color: #3eb489">
  <head>
    <title>Students table</title>
  </head>
  <body>
  <h1>Students table</h1>
  <table border="1px" style="font-size: 24px">
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
  <nav>
    <ul style="font-size: 20px; line-height: 1.5;">
      <li><a href="${pageContext.request.contextPath}">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/students-by-last-name-all-info">Students sorted by last name</a></li>
      <li><a href="${pageContext.request.contextPath}/students-by-courses-number-sorted-by-last-name">
        Students grouped by courses number and sorted by last name</a></li>
    </ul>
  </nav>
  <footer>
    <p style="color: rebeccapurple">OnlineSchool web-api</p>
  </footer>
  </body>
</html>
