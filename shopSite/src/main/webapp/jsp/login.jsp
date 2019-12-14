<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <title>Login</title>
    <link rel="stylesheet" href="../css/style.css">
</head>

<body>
<div class="container">
    <form class="form" method="post" action="/login">

        <h2 class="form-heading">Войти</h2>
        <br>

        <label for="login" class="sr-only">Логин</label>
        <input name="login" type="text" id="login" class="form-control" placeholder="username" required=""
               autofocus="">
        <br>

        <label for="inputPassword" class="sr-only">Пароль</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="password"
               required="">

        <br>

        <button class="btn btn-lg btn-success btn-block" type="submit">Отправить</button>
        <div>
            Первый раз? <a href="/registration" class="link" style="color: aqua">Зарегистрируйся</a>.
        </div>

        <br>

        <c:if test="${loginStatus != null}">
        <div class="alert alert-danger loginStatus" role="alert">
        <c:out value="${loginStatus}"/>
        </div>
        </c:if>

    </form>
</div>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</body>
</html>