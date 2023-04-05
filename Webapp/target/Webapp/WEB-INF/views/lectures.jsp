<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html style="background-color: #3eb489">
  <head>
    <title>Lectures table</title>
  </head>
  <body>
  <h1>Lectures table</h1>
  <table border="1px" style="font-size: 24px">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Amount</th>
      <th>Description</th>
      <th>Creation date</th>
      <th>Lecture date</th>
      <th>Teacher ID</th>
      <th>Course ID</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="list" type="java.util.List"--%>
    <c:forEach var="lecture" items="${list}">
      <tr>
        <td>${lecture.ID}</td>
        <td>${lecture.name}</td>
        <td>${lecture.amount}</td>
        <td>${lecture.description}</td>
        <td>${lecture.creationDate}</td>
        <td>${lecture.lectureDate}</td>
        <td>${lecture.teacherID}</td>
        <td>${lecture.courseID}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <nav>
    <ul style="font-size: 20px; line-height: 1.5;">
      <li><a href="${pageContext.request.contextPath}">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/earliest-lecture-with-most-homeworks">
        Earliest lecture with most homeworks</a></li>
      <li><a href="${pageContext.request.contextPath}/lectures-sorted-by-date">
        Lectures until 2024 sorted by lecture date</a></li>
    </ul>
  </nav>
  <footer>
    <p style="color: rebeccapurple">OnlineSchool web-api</p>
  </footer>
  </body>
</html>
