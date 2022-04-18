<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp"/>

<h1>Search</h1>
<br>

<form action="/user/search" method="get">

    <input type="text" name="searchTeamName" id="searchBoxId" placeholder="Search..." value="${searchTeamName}">
    <br><br><br>

    <button id="searchId" type="submit">Search</button>

    <h1>Search</h1>
</form>

<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Team Name</th>
        <th scope="col">Email</th>
        <%--<th scope="col">Role</th>--%> <%--TODO--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.teamName}</td>
            <td>${user.email}</td>
            <%--<td>${user.role}</td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../include/footer.jsp"/>