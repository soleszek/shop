<html>

<head>
    <title>Logowanie</title>

    <link rel="stylesheet" href="style/general.css" type="text/css" >
    <link rel="stylesheet" href="style/index.css" type="text/css" >

</head>

<body>
<div id="container">
    <h1 class="panel-title">Login form</h1>
    <form class="login-form" action="LoginServlet" method="get">
            <div class="field">
                <label>
                <input type="text" name="username" placeholder="login" onfocus="this.placeholder=''" onblur="this.placeholder='login'">
                </label>
            </div>

            <div class="field">
                <label>
                    <input type="password" name="password" placeholder="password" onfocus="this.placeholder=''" onblur="this.placeholder='password'">
                </label>
            </div>
            <div class="commands">
                <input type="submit" tabindex="5" value="login">
            </div>
    </form>

    <ul class="create-user">
        <li>
            <a href="rejestracja.jsp" tabindex="7">Create user</a>
        </li>
    </ul>

</div>

</body>
</html>
