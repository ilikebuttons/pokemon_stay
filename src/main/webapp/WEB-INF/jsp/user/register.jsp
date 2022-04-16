<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../include/header.jsp"/>

<form action="/user/registerSubmit" method="post">
                        <input type="hidden"    name="id"                   id="id"                     value="${form.id}"><br>
    Email               <input type="email"     name="email"                id="emailId"                value="${form.email}"><br>
    Team Name           <input type="text"      name="teamName"             id="teamNameId"             value="${form.teamName}"><br>
    Password            <input type="password"  name="password"             id="passwordId"             value="${form.password}"><br>
    Confirm Password    <input type="password"  name="confirmPassword"      id="confirmPasswordId"      value="${form.confirmPassword}"><br>

    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp"/>