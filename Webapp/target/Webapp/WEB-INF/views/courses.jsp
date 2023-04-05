<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html style="background-color: #3eb489">
  <head>
    <title>Courses table</title>
  </head>
  <body>
  <h1>Courses table</h1>
  <table border="1px" style="font-size: 24px">
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
  <nav>
    <ul style="font-size: 20px; line-height: 1.5;">
      <li><a href="${pageContext.request.contextPath}">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/add-new-course">Create new course</a></li>
    </ul>
  </nav>
  <footer>
    <p style="color: rebeccapurple">OnlineSchool web-api</p>
  </footer>
  </body>
</html>
