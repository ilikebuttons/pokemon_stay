<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>

<form action="/user/registerSubmit" method="post">
                        <input type="hidden"    name="id"                   id="id"                     value="${form.id}">

    Email               <input type="text"      name="email"                id="emailId"                value="${form.email}">
                        <c:forEach items='${bindingResult.getFieldErrors("email")}' var="error"><div class="error">${error.getDefaultMessage()}</div></c:forEach><br>

    Team Name           <input type="text"      name="teamName"             id="teamNameId"             value="${form.teamName}">
                        <c:forEach items='${bindingResult.getFieldErrors("firstName")}' var="error"><div class="error">${error.getDefaultMessage()}</div></c:forEach><br>

    Password            <input type="password"  name="password"             id="passwordId"             value="${form.password}">
                        <c:forEach items='${bindingResult.getFieldErrors("password")}' var="error"><div class="error">${error.getDefaultMessage()}</div></c:forEach><br>

    Confirm Password    <input type="password"  name="confirmPassword"      id="confirmPasswordId"      value="${form.confirmPassword}">
                        <c:forEach items='${bindingResult.getFieldErrors("confirmPassword")}' var="error"><div class="error">${error.getDefaultMessage()}</div></c:forEach><br>

    <button type="submit">Submit</button>
</form>

<c:if test="${bindingResult.hasErrors()}">
    <br>

    <c:forEach items="${bindingResult.getAllErrors()}" var="error">
        <div class="error">${error.getDefaultMessage()}</div>
    </c:forEach>
</c:if>

<jsp:include page="../include/footer.jsp"/>