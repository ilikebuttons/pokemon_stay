<jsp:include page="../include/header.jsp"/>

<form action="/user/loginSubmit" method="POST">

    Username: <input type="text" name="username">
    <br>
    Password: <input type="password" name="password">
    <br>
    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp"/>