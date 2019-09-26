<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 23.09.2019
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<h1>Hello, ${name}</h1>
<h3>Welcome to ${group}</h3>
<form method="post">
    <h2>
        Name:
        <input type="text" name="name"/><br>
        Group:
        <input type="text" name="group"/><br>
        <INPUT type="submit" value="Send">
    </h2>
</form>
</body>
</html>
