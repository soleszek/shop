<html>

<head>
    <title>Logowanie</title>

<link rel="stylesheet" href="style/style.css" type="text/css" >

</head>

<body>
<div class="login-panel">
    <h1 class="panel-title">Login form</h1>
    <form class="login-form" action="LoginServlet" method="post">
        <fieldset>
            <div class="field">
                <label>
                    <span class="label">Username:</span>
                <input class="username-input-text" type="text" name="username">
                </label>
            </div>

            <div class="field">
                <label>
                    <span class="label">Password:</span>
                    <input class="password-input-text" type="text" name="password">
                </label>
            </div>
            <div class="commands">
                <input class="input-submit" type="submit" tabindex="5" value="login">
            </div>
        </fieldset>
    </form>

    <ul class="create-user">
        <li>
            <a href="rejestracja.jsp" tabindex="7">Create user</a>
        </li>
    </ul>

</div>

</body>
</html>
