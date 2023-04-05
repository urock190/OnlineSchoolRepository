<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html style="background-color: #3eb489">
  <head>
    <title>Earliest Lecture With Most Homeworks</title>
  </head>
  <body>
  <h1>Earliest lecture with most homeworks</h1>
  <%--@elvariable id="lecture" type="src.main.java.models.Lecture"--%>
  <div style="font-size: 24px">
    <p>ID: ${lecture.ID}</p>
    <p>Name: ${lecture.name}</p>
    <p>Amount: ${lecture.amount}</p>
    <p>Description: ${lecture.description}</p>
    <p>Creation date: ${lecture.creationDate}</p>
    <p>Lecture date: ${lecture.lectureDate}</p>
    <p>Teacher ID: ${lecture.teacherID}</p>
    <p>Course ID: ${lecture.courseID}</p>
  </div>
  <nav>
    <ul style="font-size: 20px; line-height: 1.5;">
      <li><a href="${pageContext.request.contextPath}">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/lectures">Back to Lectures table</a></li>
    </ul>
  </nav>
  </body>
</html>
