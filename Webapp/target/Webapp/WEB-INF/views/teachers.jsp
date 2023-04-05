<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html style="background-color: #3eb489">
  <head>
    <title>Teachers table</title>
  </head>
  <body>
  <h1>Teachers table</h1>
  <table border="1px"; style="font-size: 24px">
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
  <nav>
    <ul style="font-size: 20px; line-height: 1.5;">
      <li><a href="${pageContext.request.contextPath}">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/teachers-with-last-name-to-the-letter-N">
        Teachers with last name to the letter N</a></li>
    </ul>
  </nav>
  <footer>
    <p style="color: rebeccapurple">OnlineSchool web-api</p>
  </footer>
  </body>
</html>
