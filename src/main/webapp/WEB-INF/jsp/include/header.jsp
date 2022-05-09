<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pok&#233;mon Stay</title>
    <link rel="icon" type="image/png" href="../../../pub/images/favicon.png">

    <!-- JQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

    <!-- Bootstrap -->
    <%--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>--%>

    <!-- Fonts -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/firacode@6.2.0/distr/fira_code.css">

    <!-- CSS -->
    <link rel="stylesheet" href="../../../pub/css/header.css">
    <link rel="stylesheet" href="../../../pub/css/footer.css">
    <link rel="stylesheet" href="../../../pub/css/game.css">
    <link rel="stylesheet" href="../../../pub/css/slider.css">
    <link rel="stylesheet" href="../../../pub/css/register.css">
    <link rel="stylesheet" href="../../../pub/css/assign_to_area.css">
</head>

<body>
    <header class="header" ondragstart="return false">
        <a href="/game" id="nav-title">Pok&#233;mon Stay</a>
        <nav>
            <ul>
                <sec:authorize access="hasAuthority('ADMIN')">
                    <li><a href="/user/search">Users</a></li>
                    <li><a href="/admin/locations">Locations</a></li>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <li><a href="/user/register">Register</a></li>
                    <li><a href="/user/login">Login</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li><a href="/game/trainers">Trainers</a></li>
                    <li><img src="../../../pub/images/pokecoin.png" width="24px" height="24px" alt=""> ${param.coins}</li>
                    <li class="fa fa-caret-down">${param.teamName}
                        <div class="dropdown-content">
                            <a href="#">Messages</a>
                            <a style="display:inline-block" href="#">
                                <input class="dmcheck" type="checkbox" checked>
                                Dark Mode
                            </a>
                            <sec:authorize access="isAuthenticated()">
                                <a href="/user/logout">Log Out</a>
                            </sec:authorize>
                        </div>
                    </li>
                </sec:authorize>
            </ul>
        </nav>
    </header>
<br><br><br>