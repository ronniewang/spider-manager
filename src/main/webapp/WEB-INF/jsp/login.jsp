<html>
<head></head>
<body>
<script src="/spider-web/js/jquery.min.js-2.2.1.js"></script>
<script>
    $('.message a').click(function () {
        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });
</script>
<link href="/spider-web/css/login.css" rel="stylesheet">
<div class="login-page">
    <div class="form">
        <form class="register-form">
            <input type="text" placeholder="name"/>
            <input type="password" placeholder="password"/>
            <input type="text" placeholder="email address"/>
            <button>create</button>
            <p class="message">Already registered? <a href="#">Sign In</a></p>
        </form>
        <form class="login-form" action="/login" method="post">
            <input type="text" placeholder="username" id="username" name="username"/>
            <input type="password" placeholder="password" id="password" name="password"/>
            <button type="submit">login</button>
            <%--<p class="message">Not registered? <a href="#">Create an account</a></p>--%>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

            <p class="err-msg">
                ${error}
            </p>
        </form>
    </div>
</div>
</body>
</html>
