<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pok&#233;mon Stay</title>

    <!-- JQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

    <!-- Bootstrap -->
    <%--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>--%>

    <!-- Fonts -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/firacode@6.2.0/distr/fira_code.css">

    <!-- CSS -->
    <link rel="stylesheet" href="../../../pub/css/header.css">
</head>

<body>

<header>
    <h1 class="fa fa-caret-down" id="nav-title">Pok&#233;mon Stay</h1>
    <div class="dropdown-content">
        <a href="#">Link 1</a>
        <a href="#">Link 2</a>
        <a href="#">Link 3</a>
    </div>

    <nav>
        <ul>
            <sec:authorize access="hasAuthority('ADMIN')">
                <li><a href="/user/search">Search</a></li>
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                <li><a href="/user/register">Register</a></li>
                <li><a href="/user/login">Login</a></li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <li><a href="/user/logout">Logout</a></li>
                <li><sec:authentication property="principal.username" /></li>
            </sec:authorize>
        </ul>
    </nav>
</header>
<br><br><br>