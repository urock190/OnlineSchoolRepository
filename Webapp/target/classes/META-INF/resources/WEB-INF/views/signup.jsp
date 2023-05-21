<%@ page contentType="text/html;charset=UTF-8" %>

<html class="javascript">
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
    <script src="${pageContext.request.contextPath}/static/css/signupScript.js" defer></script>
</head>
<!--User registration -->
<body style="width: auto;">
<h1 style="text-align: center; margin-bottom: 40px;">Sign up</h1>
<form action="${pageContext.request.contextPath}/registration" method="post" style="margin: 0 auto; width: 400px;">
    <div style="margin-top: 1em;">
        <label for="firstName" class="login-label">First name: </label>
        <br>
        <input type="text" name="firstName" id="firstName" required class="login-input">
        <span id="firstNameError" style="margin-left: 75px; color: yellow"> First name incorrect </span>
    </div>
    <div style="margin-top: 1em;">
        <label for="lastName" class="login-label">Last name: </label>
        <br>
        <input type="text" name="lastName" id="lastName" required class="login-input">
        <span id="lastNameError" style="margin-left: 75px; color: yellow"> Last name incorrect </span>
    </div>
    <div style="margin-top: 1em;">
        <label for="username" class="login-label">Username or email: </label>
        <br>
        <input type="text" name="username" id="username" required class="login-input">
        <span id="nameError" style="margin-left: 75px; color: yellow"> Username incorrect </span>
    </div>
    <div style="margin-top: 1em;">
        <label for="password" class="login-label">Password: </label>
        <br>
        <input type="password" name="password" id="password" required class="login-input">
        <span id="passwordError" style="margin-left: 75px; color: yellow"> Password incorrect </span>
    </div>
    <div style="margin-top: 1em;">
        <label for="matchingPassword" class="login-label">Confirm password: </label>
        <br>
        <input type="password" name="matchingPassword" id="matchingPassword" required class="login-input">
        <span id="confirmationError" style="margin-left: 75px; color: yellow"> Passwords dont match </span>
    </div>
    <div style="margin-top: 1.2em;">
        <input type="submit" value="Sign up" class="login-submit">
    </div>
</form>
<nav class="nav">
    <ul>
        <li><a href="${pageContext.request.contextPath}/login">Log in</a></li>
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
    </ul>
</nav>
</body>
</html>