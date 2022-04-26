<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp"/>

<h1>Locations</h1>
<br>

<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Description / Biomes</th>
        <%--<th scope="col">Role</th>--%> <%--TODO--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${locations}" var="location">
        <tr>
            <td>${location.id}</td>
            <td>${location.name}</td>
            <td>${location.description}</td>
            <td><a href="admin/generator/${location.id}">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../include/footer.jsp"/>