<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="background-color: #c9b307">
  <head>
    <title>Create new course</title>
  </head>
  <body>
  <div>
    <h1>Create new course</h1>
    <form action="${pageContext.request.contextPath}/add-new-course" method="post" style="margin: 0 auto; width: 400px;">
      <div style="margin-top: 1em;">
        <label for="name" style="display: inline-block; width: 100px; text-align: right;">Name: </label>
        <input type="text" name="name" id="name" required>
      </div>
      <div style="margin-top: 1em;">
        <label for="ID" style="display: inline-block; width: 100px; text-align: right;">ID: </label>
        <input type="number" min="1" name="ID" id="ID" required>
      </div>
      <div style="margin-top: 1em; padding-left: 100px;">
        <input type="submit" value="Insert" style="margin-left: 0.5em;">
      </div>
    </form>
    <nav>
      <ul style="font-size: 20px; line-height: 1.5;">
        <li><a href="${pageContext.request.contextPath}">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/courses">Back to Courses table</a></li>
      </ul>
    </nav>
  </div>
  </body>
</html>
