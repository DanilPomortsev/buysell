<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h4>Авторизация</h4>
<form action="/login" method="post">
    <div class="form-group">
        <label for="email">Электронная почта</label>
        <input type="email" name="username"><br><br>
    </div>
    <div class="form-group">
        <label for="password">Пароль</label>
        <input type="password" name="password"><br><br>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<a href="/registration">Зарегистрироваться</a>
</body>
</html>