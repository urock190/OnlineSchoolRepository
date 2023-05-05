<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="table">
  <head>
    <title>Earliest Lecture With Most Homeworks</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
  </head>
  <body>
  <h1>Earliest lecture with most homeworks</h1>
  <%--@elvariable id="lecture" type="src.main.java.models.Lecture"--%>
  <div>
    <p>ID: ${lecture.ID}</p>
    <p>Name: ${lecture.name}</p>
    <p>Amount: ${lecture.amount}</p>
    <p>Description: ${lecture.description}</p>
    <p>Creation date: ${lecture.creationDate}</p>
    <p>Lecture date: ${lecture.lectureDate}</p>
    <p>Teacher ID: ${lecture.teacherID}</p>
    <p>Course ID: ${lecture.courseID}</p>
  </div>
  <nav class="nav">
    <ul>
      <li><a href="${pageContext.request.contextPath}/">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/lectures">Back to Lectures table</a></li>
    </ul>
  </nav>
  </body>
</html>
